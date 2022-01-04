package spark.ml;

import org.apache.spark.ml.attribute.Attribute;
import org.apache.spark.ml.feature.*;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.linalg.Vectors;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.collection.mutable.WrappedArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.apache.spark.sql.functions.callUDF;
import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.types.DataTypes.*;

/**
 * @author damon
 * @version 2018/9/12
 */
public class FeatureTransformers {

    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder().master("local").appName("mySpark").getOrCreate();
        sparkSession.sparkContext().setLogLevel("WARN");

//        tokenizer(sparkSession);
//        stopWordsRemover(sparkSession);
//        nGram(sparkSession);
//        binarizer(sparkSession);
//        pca(sparkSession);
//        polynomialExpansion(sparkSession);
//        dct(sparkSession);
//        stringIndexer(sparkSession);
//        indexToString(sparkSession);
//        oneHotEncodeEstimator(sparkSession);
//        vectorIndexer(sparkSession);
//        interaction(sparkSession);
//        normalizer(sparkSession);
//        minMaxScaler(sparkSession);
//        maxAbsScaler(sparkSession);
//        bucketizer(sparkSession);
//        elementwiseProduct(sparkSession);
//        sqlTransformer(sparkSession);
//        vectorAssembler(sparkSession);
//        vectorSizeHint(sparkSession);
//        quantileDiscretizer(sparkSession);
        imputer(sparkSession);
    }

    private static void tokenizer(SparkSession sparkSession) {
        List<Row> data = Arrays.asList(
                RowFactory.create(0, "Hi I heard about Spark"),
                RowFactory.create(1, "I wish Java could use case classes"),
                RowFactory.create(2, "Logistic,regression,models,are,neat")
        );

        StructType schema = new StructType(new StructField[]{
                new StructField("id", IntegerType, false, Metadata.empty()),
                new StructField("sentence", StringType, false, Metadata.empty())
        });

        Dataset<Row> sentenceData = sparkSession.createDataFrame(data, schema);

        Tokenizer tokenizer = new Tokenizer()
                .setInputCol("sentence")
                .setOutputCol("words");

        RegexTokenizer regexTokenizer = new RegexTokenizer()
                .setInputCol("sentence")
                .setOutputCol("words")
                .setPattern("\\W"); // 或者 .setPattern("\\w+").setGaps(false);

        sparkSession.udf().register("countTokens", (WrappedArray<?> words) -> words.size(), IntegerType);

        Dataset<Row> tokenized = tokenizer.transform(sentenceData);
        tokenized.select("sentence", "words")
                .withColumn("tokens", callUDF("countTokens", col("words")))
                .show(false);

        Dataset<Row> regexTokenized = regexTokenizer.transform(sentenceData);
        regexTokenized.select("sentence", "words")
                .withColumn("tokens", callUDF("countTokens", col("words")))
                .show(false);
    }

    private static void stopWordsRemover(SparkSession sparkSession) {
        List<Row> data = Arrays.asList(
                RowFactory.create(Arrays.asList("I", "saw", "the", "red", "balloon")),
                RowFactory.create(Arrays.asList("Mary", "had", "a", "little", "lamb"))
        );

        StructType schema = new StructType(new StructField[]{
                new StructField(
                        "raw", DataTypes.createArrayType(StringType), false, Metadata.empty())
        });

        Dataset<Row> dataset = sparkSession.createDataFrame(data, schema);

        StopWordsRemover stopWordsRemover = new StopWordsRemover()
                .setInputCol("raw")
                .setOutputCol("filtered");

        stopWordsRemover.transform(dataset).show(false);
    }

    private static void nGram(SparkSession sparkSession) {
        List<Row> data = Arrays.asList(
                RowFactory.create(0, Arrays.asList("Hi", "I", "heard", "about", "Spark")),
                RowFactory.create(1, Arrays.asList("I", "wish", "Java", "could", "use", "case", "classes")),
                RowFactory.create(2, Arrays.asList("Logistic", "regression", "models", "are", "neat"))
        );

        StructType schema = new StructType(new StructField[]{
                new StructField("id", IntegerType, false, Metadata.empty()),
                new StructField("words", DataTypes.createArrayType(StringType), false, Metadata.empty())
        });

        Dataset<Row> wordsData = sparkSession.createDataFrame(data, schema);

        NGram nGram = new NGram()
                .setN(2)
                .setInputCol("words")
                .setOutputCol("nGrams");

        Dataset<Row> nGramsData = nGram.transform(wordsData);
        nGramsData.select("nGrams").show(false);
    }

    private static void binarizer(SparkSession sparkSession) {
        List<Row> data = Arrays.asList(
                RowFactory.create(0, 0.1),
                RowFactory.create(1, 0.8),
                RowFactory.create(2, 0.2)
        );

        StructType schema = new StructType(new StructField[]{
                new StructField("id", IntegerType, false, Metadata.empty()),
                new StructField("feature", DataTypes.DoubleType, false, Metadata.empty())
        });

        Dataset<Row> dataset = sparkSession.createDataFrame(data, schema);

        Binarizer binarizer = new Binarizer()
                .setInputCol("feature")
                .setOutputCol("binarizer_feature")
                .setThreshold(0.5);

        Dataset<Row> binarizerData = binarizer.transform(dataset);

        System.out.println("Binarizer output with Threshold = " + binarizer.getThreshold());
        binarizerData.select("binarizer_feature").show(false);
    }

    private static void pca(SparkSession sparkSession) {
        List<Row> data = Arrays.asList(
                RowFactory.create(Vectors.sparse(5, new int[]{1, 3}, new double[]{1.0, 7.0})),
                RowFactory.create(Vectors.dense(2.0, 0.0, 3.0, 4.0, 5.0)),
                RowFactory.create(Vectors.dense(4.0, 0.0, 0.0, 6.0, 7.0))
        );

        StructType schema = new StructType(new StructField[]{
                new StructField("features", new VectorUDT(), false, Metadata.empty()),
        });

        Dataset<Row> dataset = sparkSession.createDataFrame(data, schema);

        PCAModel pcaModel = new PCA().setInputCol("features").setOutputCol("pca_features").setK(3).fit(dataset);

        Dataset<Row> pcaData = pcaModel.transform(dataset);

        pcaData.select("pca_features").show(false);
    }

    private static void polynomialExpansion(SparkSession sparkSession) {
        List<Row> data = Arrays.asList(
                RowFactory.create(Vectors.dense(2.0, 1.0)),
                RowFactory.create(Vectors.dense(0.0, 0.0)),
                RowFactory.create(Vectors.dense(3.0, -1.0))
        );

        StructType schema = new StructType(new StructField[]{
                new StructField("features", new VectorUDT(), false, Metadata.empty()),
        });

        Dataset<Row> dataset = sparkSession.createDataFrame(data, schema);

        PolynomialExpansion polynomialExpansion = new PolynomialExpansion()
                .setInputCol("features")
                .setOutputCol("poly_features")
                .setDegree(3);

        polynomialExpansion.transform(dataset).show(false);
    }

    private static void dct(SparkSession sparkSession) {
        List<Row> data = Arrays.asList(
                RowFactory.create(Vectors.dense(0.0, 1.0, -2.0, 3.0)),
                RowFactory.create(Vectors.dense(-1.0, 2.0, 4.0, -7.0)),
                RowFactory.create(Vectors.dense(14.0, -2.0, -5.0, 1.0))
        );

        StructType schema = new StructType(new StructField[]{
                new StructField("features", new VectorUDT(), false, Metadata.empty()),
        });

        Dataset<Row> dataset = sparkSession.createDataFrame(data, schema);

        DCT dct = new DCT()
                .setInputCol("features")
                .setOutputCol("dct_features")
                .setInverse(false);

        Dataset<Row> dctDf = dct.transform(dataset);

        dctDf.select("dct_features").show(false);
    }

    private static void stringIndexer(SparkSession sparkSession) {
        List<Row> data = Arrays.asList(
                RowFactory.create(0, "a"),
                RowFactory.create(1, "b"),
                RowFactory.create(2, "c"),
                RowFactory.create(3, "a"),
                RowFactory.create(4, "a"),
                RowFactory.create(5, "c")
        );

        StructType schema = new StructType(new StructField[]{
                createStructField("id", IntegerType, false),
                createStructField("category", StringType, false)
        });

        Dataset<Row> dataset = sparkSession.createDataFrame(data, schema);

        StringIndexer indexer = new StringIndexer()
                .setInputCol("category")
                .setOutputCol("categoryIndex");

        Dataset<Row> indexed = indexer.fit(dataset).transform(dataset);
        indexed.show();

        List<Row> test = Arrays.asList(
                RowFactory.create(0, "a"),
                RowFactory.create(1, "b"),
                RowFactory.create(2, "c"),
                RowFactory.create(3, "d"),
                RowFactory.create(4, "e")
        );
        Dataset<Row> testData = sparkSession.createDataFrame(test, schema);

        // 使用默认策略
//        Dataset<Row> testIndexed = indexer.fit(dataset).transform(testData);
//        System.out.println("默认策略结果");
//        testIndexed.show();

        // 使用跳过策略
        StringIndexer skipIndexer = new StringIndexer()
                .setInputCol("category")
                .setOutputCol("categoryIndex")
                .setHandleInvalid("skip");
        Dataset<Row> skipIndexed = skipIndexer.fit(dataset).transform(testData);
        System.out.println("跳过策略结果");
        skipIndexed.show();

        // 使用保留策略，索引号为标签个数，即3.0
        StringIndexer keepIndexer = new StringIndexer()
                .setInputCol("category")
                .setOutputCol("categoryIndex")
                .setHandleInvalid("keep");
        Dataset<Row> keepIndexed = keepIndexer.fit(dataset).transform(testData);
        System.out.println("保留策略结果");
        keepIndexed.show();
    }

    private static void indexToString(SparkSession sparkSession) {
        List<Row> data = Arrays.asList(
                RowFactory.create(0, "a"),
                RowFactory.create(1, "b"),
                RowFactory.create(2, "c"),
                RowFactory.create(3, "a"),
                RowFactory.create(4, "a"),
                RowFactory.create(5, "c")
        );
        StructType schema = new StructType(new StructField[]{
                new StructField("id", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("category", DataTypes.StringType, false, Metadata.empty())
        });
        Dataset<Row> dataset = sparkSession.createDataFrame(data, schema);

        StringIndexerModel indexer = new StringIndexer()
                .setInputCol("category")
                .setOutputCol("categoryIndex")
                .fit(dataset);

        Dataset<Row> indexed = indexer.transform(dataset);

        System.out.println("Transformed string column '" + indexer.getInputCol() + "' " +
                "to indexed column '" + indexer.getOutputCol() + "'");
        indexed.show();

        StructField inputColSchema = indexed.schema().apply(indexer.getOutputCol());
        System.out.println("StringIndexer will store labels in output column metadata: " +
                Attribute.fromStructField(inputColSchema).toString() + "\n");

        IndexToString converter = new IndexToString()
                .setInputCol("categoryIndex")
                .setOutputCol("originalCategory");
        Dataset<Row> converted = converter.transform(indexed);

        System.out.println("Transformed indexed column '" + converter.getInputCol() + "' back to " +
                "original string column '" + converter.getOutputCol() + "' using labels in metadata");
        converted.select("id", "categoryIndex", "originalCategory").show();
    }

    private static void oneHotEncodeEstimator(SparkSession sparkSession) {
        List<Row> data = Arrays.asList(
                RowFactory.create(0.0, 1.0),
                RowFactory.create(1.0, 0.0),
                RowFactory.create(2.0, 1.0),
                RowFactory.create(0.0, 2.0),
                RowFactory.create(0.0, 1.0),
                RowFactory.create(2.0, 0.0)
        );

        StructType schema = new StructType(new StructField[]{
                new StructField("categoryIndex1", DataTypes.DoubleType, false, Metadata.empty()),
                new StructField("categoryIndex2", DataTypes.DoubleType, false, Metadata.empty())
        });

        Dataset<Row> dataset = sparkSession.createDataFrame(data, schema);

        OneHotEncoderEstimator oneHotEncoderEstimator = new OneHotEncoderEstimator()
                .setInputCols(new String[]{"categoryIndex1", "categoryIndex2"})
                .setOutputCols(new String[]{"categoryVec1", "categoryVec2"});

        OneHotEncoderModel model = oneHotEncoderEstimator.fit(dataset);
        Dataset<Row> encoded = model.transform(dataset);
        encoded.show(false);
    }

    private static void vectorIndexer(SparkSession sparkSession) {
        Dataset<Row> data = sparkSession.read().format("libsvm").load("data/mllib/sample_libsvm_data.txt");

        VectorIndexer indexer = new VectorIndexer()
                .setInputCol("features")
                .setOutputCol("indexed")
                .setMaxCategories(10);
        VectorIndexerModel indexerModel = indexer.fit(data);

        Map<Integer, Map<Double, Integer>> categoryMaps = indexerModel.javaCategoryMaps();
        System.out.print("Chose " + categoryMaps.size() + " categorical features:");

        for (Integer feature : categoryMaps.keySet()) {
            System.out.print(" " + feature);
        }
        System.out.println();

        // 使用转换为索引的分类值创建新的“索引”列
        Dataset<Row> indexedData = indexerModel.transform(data);
        indexedData.show();
    }

    private static void interaction(SparkSession sparkSession) {
        List<Row> data = Arrays.asList(
                RowFactory.create(1, 1, 2, 3, 8, 4, 5),
                RowFactory.create(2, 4, 3, 8, 7, 9, 8),
                RowFactory.create(3, 6, 1, 9, 2, 3, 6),
                RowFactory.create(4, 10, 8, 6, 9, 4, 5),
                RowFactory.create(5, 9, 2, 7, 10, 7, 3),
                RowFactory.create(6, 1, 1, 4, 2, 8, 4)
        );

        StructType schema = new StructType(new StructField[]{
                new StructField("id1", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("id2", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("id3", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("id4", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("id5", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("id6", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("id7", DataTypes.IntegerType, false, Metadata.empty())
        });

        Dataset<Row> df = sparkSession.createDataFrame(data, schema);

        VectorAssembler assembler1 = new VectorAssembler()
                .setInputCols(new String[]{"id2", "id3", "id4"})
                .setOutputCol("vec1");

        Dataset<Row> assembled1 = assembler1.transform(df);

        VectorAssembler assembler2 = new VectorAssembler()
                .setInputCols(new String[]{"id5", "id6", "id7"})
                .setOutputCol("vec2");

        Dataset<Row> assembled2 = assembler2.transform(assembled1).select("id1", "vec1", "vec2");

        Interaction interaction = new Interaction()
                .setInputCols(new String[]{"id1","vec1","vec2"})
                .setOutputCol("interactedCol");

        Dataset<Row> interacted = interaction.transform(assembled2);

        interacted.show(false);
    }

    private static void normalizer(SparkSession sparkSession) {
        List<Row> data = Arrays.asList(
                RowFactory.create(0, Vectors.dense(1.0, 0.1, -8.0)),
                RowFactory.create(1, Vectors.dense(2.0, 1.0, -4.0)),
                RowFactory.create(2, Vectors.dense(4.0, 10.0, 8.0))
        );
        StructType schema = new StructType(new StructField[]{
                new StructField("id", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("features", new VectorUDT(), false, Metadata.empty())
        });
        Dataset<Row> dataFrame = sparkSession.createDataFrame(data, schema);

        Normalizer normalizer = new Normalizer()
                .setInputCol("features")
                .setOutputCol("normFeatures")
                .setP(1.0);

        Dataset<Row> l1NormData = normalizer.transform(dataFrame);
        l1NormData.show(false);

        Dataset<Row> lInfNormData =
                normalizer.transform(dataFrame, normalizer.p().w(Double.POSITIVE_INFINITY));
        lInfNormData.show(false);
    }

    private static void standardScaler(SparkSession sparkSession) {
        Dataset<Row> dataFrame = sparkSession.read().format("libsvm").load("data/mllib/sample_libsvm_data.txt");

        StandardScaler scaler = new StandardScaler()
                .setInputCol("features")
                .setOutputCol("scaledFeatures")
                .setWithStd(true)
                .setWithMean(false);

        StandardScalerModel scalerModel = scaler.fit(dataFrame);

        Dataset<Row> scaledData = scalerModel.transform(dataFrame);
        scaledData.show(false);
    }

    private static void minMaxScaler(SparkSession sparkSession) {
        List<Row> data = Arrays.asList(
                RowFactory.create(0, Vectors.dense(1.0, 0.1, -1.0)),
                RowFactory.create(1, Vectors.dense(2.0, 1.1, 1.0)),
                RowFactory.create(2, Vectors.dense(3.0, 10.1, 3.0))
        );
        StructType schema = new StructType(new StructField[]{
                new StructField("id", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("features", new VectorUDT(), false, Metadata.empty())
        });
        Dataset<Row> dataFrame = sparkSession.createDataFrame(data, schema);

        MinMaxScaler minMaxScaler = new MinMaxScaler().setInputCol("features").setOutputCol("scaledFeatures");

        MinMaxScalerModel scalerModel = minMaxScaler.fit(dataFrame);

        Dataset<Row> scaledData = scalerModel.transform(dataFrame);
        System.out.println("Features scaled to range: [" + minMaxScaler.getMin() + ", " + minMaxScaler.getMax() + "]");
        scaledData.select("features", "scaledFeatures").show();
    }

    private static void maxAbsScaler(SparkSession sparkSession) {
        List<Row> data = Arrays.asList(
                RowFactory.create(0, Vectors.dense(1.0, 0.1, -8.0)),
                RowFactory.create(1, Vectors.dense(2.0, 1.0, -4.0)),
                RowFactory.create(2, Vectors.dense(4.0, 10.0, 8.0))
        );
        StructType schema = new StructType(new StructField[]{
                new StructField("id", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("features", new VectorUDT(), false, Metadata.empty())
        });
        Dataset<Row> dataFrame = sparkSession.createDataFrame(data, schema);

        MaxAbsScaler scaler = new MaxAbsScaler()
                .setInputCol("features")
                .setOutputCol("scaledFeatures");

        MaxAbsScalerModel scalerModel = scaler.fit(dataFrame);

        Dataset<Row> scaledData = scalerModel.transform(dataFrame);
        scaledData.select("features", "scaledFeatures").show();
    }

    private static void bucketizer(SparkSession sparkSession) {
        double[] splits = {Double.NEGATIVE_INFINITY, -0.5, 0.0, 0.5, Double.POSITIVE_INFINITY};

        List<Row> data = Arrays.asList(
                RowFactory.create(-999.9),
                RowFactory.create(-0.5),
                RowFactory.create(-0.3),
                RowFactory.create(0.0),
                RowFactory.create(0.2),
                RowFactory.create(999.9)
        );
        StructType schema = new StructType(new StructField[]{
                new StructField("features", DataTypes.DoubleType, false, Metadata.empty())
        });
        Dataset<Row> dataFrame = sparkSession.createDataFrame(data, schema);

        Bucketizer bucketizer = new Bucketizer()
                .setInputCol("features")
                .setOutputCol("bucketedFeatures")
                .setSplits(splits);

        Dataset<Row> bucketedData = bucketizer.transform(dataFrame);

        System.out.println("Bucketizer output with " + (bucketizer.getSplits().length-1) + " buckets");
        bucketedData.show();

        double[][] splitsArray = {
                {Double.NEGATIVE_INFINITY, -0.5, 0.0, 0.5, Double.POSITIVE_INFINITY},
                {Double.NEGATIVE_INFINITY, -0.3, 0.0, 0.3, Double.POSITIVE_INFINITY}
        };

        List<Row> data2 = Arrays.asList(
                RowFactory.create(-999.9, -999.9),
                RowFactory.create(-0.5, -0.2),
                RowFactory.create(-0.3, -0.1),
                RowFactory.create(0.0, 0.0),
                RowFactory.create(0.2, 0.4),
                RowFactory.create(999.9, 999.9)
        );
        StructType schema2 = new StructType(new StructField[]{
                new StructField("features1", DataTypes.DoubleType, false, Metadata.empty()),
                new StructField("features2", DataTypes.DoubleType, false, Metadata.empty())
        });
        Dataset<Row> dataFrame2 = sparkSession.createDataFrame(data2, schema2);

        Bucketizer bucketizer2 = new Bucketizer()
                .setInputCols(new String[] {"features1", "features2"})
                .setOutputCols(new String[] {"bucketedFeatures1", "bucketedFeatures2"})
                .setSplitsArray(splitsArray);

        Dataset<Row> bucketedData2 = bucketizer2.transform(dataFrame2);

        System.out.println("Bucketizer output with [" +
                (bucketizer2.getSplitsArray()[0].length-1) + ", " +
                (bucketizer2.getSplitsArray()[1].length-1) + "] buckets for each input column");
        bucketedData2.show();
    }

    private static void elementwiseProduct(SparkSession sparkSession) {
        List<Row> data = Arrays.asList(
                RowFactory.create("a", Vectors.dense(1.0, 2.0, 3.0)),
                RowFactory.create("b", Vectors.dense(4.0, 5.0, 6.0))
        );

        List<StructField> fields = new ArrayList<>(2);
        fields.add(DataTypes.createStructField("id", DataTypes.StringType, false));
        fields.add(DataTypes.createStructField("vector", new VectorUDT(), false));

        StructType schema = DataTypes.createStructType(fields);

        Dataset<Row> dataFrame = sparkSession.createDataFrame(data, schema);

        Vector transformingVector = Vectors.dense(0.0, 1.0, 2.0);

        ElementwiseProduct transformer = new ElementwiseProduct()
                .setScalingVec(transformingVector)
                .setInputCol("vector")
                .setOutputCol("transformedVector");

        transformer.transform(dataFrame).show();
    }

    private static void sqlTransformer(SparkSession sparkSession) {
        List<Row> data = Arrays.asList(
                RowFactory.create(0, 1.0, 3.0),
                RowFactory.create(2, 2.0, 5.0)
        );
        StructType schema = new StructType(new StructField [] {
                new StructField("id", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("v1", DataTypes.DoubleType, false, Metadata.empty()),
                new StructField("v2", DataTypes.DoubleType, false, Metadata.empty())
        });
        Dataset<Row> df = sparkSession.createDataFrame(data, schema);

        SQLTransformer sqlTrans = new SQLTransformer().setStatement(
                "SELECT *, (v1 + v2) AS v3, (v1 * v2) AS v4 FROM __THIS__");

        sqlTrans.transform(df).show();
    }

    private static void vectorAssembler(SparkSession sparkSession) {
        StructType schema = createStructType(new StructField[]{
                createStructField("id", IntegerType, false),
                createStructField("hour", IntegerType, false),
                createStructField("mobile", DoubleType, false),
                createStructField("userFeatures", new VectorUDT(), false),
                createStructField("clicked", DoubleType, false)
        });
        Row row = RowFactory.create(0, 18, 1.0, Vectors.dense(0.0, 10.0, 0.5), 1.0);
        Dataset<Row> dataset = sparkSession.createDataFrame(Arrays.asList(row), schema);

        VectorAssembler assembler = new VectorAssembler()
                .setInputCols(new String[]{"hour", "mobile", "userFeatures"})
                .setOutputCol("features");

        Dataset<Row> output = assembler.transform(dataset);
        System.out.println("Assembled columns 'hour', 'mobile', 'userFeatures' to vector column " +
                "'features'");
        output.select("features", "clicked").show(false);
    }

    private static void vectorSizeHint(SparkSession sparkSession) {
        StructType schema = createStructType(new StructField[]{
                createStructField("id", IntegerType, false),
                createStructField("hour", IntegerType, false),
                createStructField("mobile", DoubleType, false),
                createStructField("userFeatures", new VectorUDT(), false),
                createStructField("clicked", DoubleType, false)
        });
        Row row0 = RowFactory.create(0, 18, 1.0, Vectors.dense(0.0, 10.0, 0.5), 1.0);
        Row row1 = RowFactory.create(0, 18, 1.0, Vectors.dense(0.0, 10.0), 0.0);
        Dataset<Row> dataset = sparkSession.createDataFrame(Arrays.asList(row0, row1), schema);

        VectorSizeHint sizeHint = new VectorSizeHint()
                .setInputCol("userFeatures")
                .setHandleInvalid("skip")
                .setSize(3);

        Dataset<Row> datasetWithSize = sizeHint.transform(dataset);
        System.out.println("Rows where 'userFeatures' is not the right size are filtered out");
        datasetWithSize.show(false);

        VectorAssembler assembler = new VectorAssembler()
                .setInputCols(new String[]{"hour", "mobile", "userFeatures"})
                .setOutputCol("features");

        Dataset<Row> output = assembler.transform(datasetWithSize);
        System.out.println("Assembled columns 'hour', 'mobile', 'userFeatures' to vector column " +
                "'features'");
        output.select("features", "clicked").show(false);
    }

    private static void quantileDiscretizer(SparkSession sparkSession) {
        List<Row> data = Arrays.asList(
                RowFactory.create(0, 18.0),
                RowFactory.create(1, 19.0),
                RowFactory.create(2, 8.0),
                RowFactory.create(3, 5.0),
                RowFactory.create(4, 2.2)
        );

        StructType schema = new StructType(new StructField[]{
                new StructField("id", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("hour", DataTypes.DoubleType, false, Metadata.empty())
        });

        Dataset<Row> df = sparkSession.createDataFrame(data, schema);

        QuantileDiscretizer discretizer = new QuantileDiscretizer()
                .setInputCol("hour")
                .setOutputCol("result")
                .setNumBuckets(3);

        Dataset<Row> result = discretizer.fit(df).transform(df);
        result.show(false);
    }

    private static void imputer(SparkSession sparkSession) {
        List<Row> data = Arrays.asList(
                RowFactory.create(1.0, Double.NaN),
                RowFactory.create(2.0, Double.NaN),
                RowFactory.create(Double.NaN, 3.0),
                RowFactory.create(4.0, 4.0),
                RowFactory.create(5.0, 5.0)
        );
        StructType schema = new StructType(new StructField[]{
                createStructField("a", DoubleType, false),
                createStructField("b", DoubleType, false)
        });
        Dataset<Row> df = sparkSession.createDataFrame(data, schema);

        Imputer imputer = new Imputer()
                .setInputCols(new String[]{"a", "b"})
                .setOutputCols(new String[]{"out_a", "out_b"});

        ImputerModel model = imputer.fit(df);
        model.transform(df).show();
    }
}
