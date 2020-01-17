package spark.ml;

import org.apache.spark.ml.feature.*;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author damon
 * @version 2018/9/11
 */
public class FeatureExtractors {

    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder().master("local").appName("mySpark").getOrCreate();
        sparkSession.sparkContext().setLogLevel("WARN");

//        tf_idf(sparkSession);
//        word2Vec(sparkSession);
//        countVectorizer(sparkSession);
        featureHasher(sparkSession);
    }

    private static void tf_idf(SparkSession sparkSession) {
        List<Row> data = Arrays.asList(
                RowFactory.create(0.0, "Hi I heard about Spark"),
                RowFactory.create(0.0, "I wish Java could use case classes"),
                RowFactory.create(1.0, "Logistic regression models are neat")
        );

        StructType schema = new StructType(new StructField[]{
                new StructField("label", DataTypes.DoubleType, false, Metadata.empty()),
                new StructField("sentence", DataTypes.StringType, false, Metadata.empty())
        });

        Dataset<Row> sentenceData = sparkSession.createDataFrame(data, schema);

        Tokenizer tokenizer = new Tokenizer()
                .setInputCol("sentence")
                .setOutputCol("words");
        Dataset<Row> wordsData = tokenizer.transform(sentenceData);

        int numFeatures = 20;
        HashingTF hashingTF = new HashingTF()
                .setInputCol("words")
                .setOutputCol("rawFeatures")
                .setNumFeatures(numFeatures);

        Dataset<Row> featurizedData = hashingTF.transform(wordsData);
        // 另外，CountVectorizer也可以用来得到词频向量

        IDF idf = new IDF()
                .setInputCol("rawFeatures")
                .setOutputCol("features");
        IDFModel idfModel = idf.fit(featurizedData);

        Dataset<Row> rescaledData = idfModel.transform(featurizedData);
        rescaledData.select("label", "features").show(false);
    }

    private static void word2Vec(SparkSession sparkSession) {
        // 输入数据：每行是一个句子或文档中的单词组
        List<Row> data = Arrays.asList(
                RowFactory.create(Arrays.asList("Hi I heard about Spark".split(" "))),
                RowFactory.create(Arrays.asList("I wish Java could use case classes".split(" "))),
                RowFactory.create(Arrays.asList("Logistic regression models are neat".split(" ")))
                );

        StructType schema = new StructType(new StructField[]{
                new StructField("text", new ArrayType(DataTypes.StringType, true), false, Metadata.empty())
        });

        Dataset<Row> documentDF = sparkSession.createDataFrame(data, schema);

        // 学习从单词到向量的映射
        Word2Vec word2Vec = new Word2Vec()
                .setInputCol("text")
                .setOutputCol("result")
                .setVectorSize(3)
                .setMinCount(0);

        Word2VecModel model = word2Vec.fit(documentDF);
        Dataset<Row> result = model.transform(documentDF);
        for (Row row : result.collectAsList()) {
            List<String> text = row.getList(0);
            Vector vector = (Vector) row.get(1);
            System.out.println("Text: " + text + " => \nVector" + vector);
        }

        List<Row> data1 = Arrays.asList(
                RowFactory.create(Arrays.asList("Hi I heard about Spark".split(" "))),
                RowFactory.create(Arrays.asList("Hi I heard about Java".split(" ")))
        );
        Dataset<Row> documentDF1 = sparkSession.createDataFrame(data1, schema);
        Dataset<Row> result1 = model.transform(documentDF1);
        System.out.println("预测其他数据集");
        for (Row row : result1.collectAsList()) {
            List<String> text = row.getList(0);
            Vector vector = (Vector) row.get(1);
            System.out.println("Text: " + text + " => \nVector" + vector);
        }
    }

    private static void countVectorizer(SparkSession sparkSession) {
        // 输入数据：每行是一个句子或文档中的单词组
        List<Row> data = Arrays.asList(
                RowFactory.create(Arrays.asList("a", "b", "c")),
                RowFactory.create(Arrays.asList("a", "b", "b", "c", "a"))
        );
        StructType schema = new StructType(new StructField [] {
                new StructField("text", new ArrayType(DataTypes.StringType, true), false, Metadata.empty())
        });
        Dataset<Row> df = sparkSession.createDataFrame(data, schema);

        CountVectorizerModel cvModel = new CountVectorizer()
                .setInputCol("text")
                .setOutputCol("feature")
                .setVocabSize(3)
                .setMinDF(2)
                .fit(df);
        cvModel.transform(df).show(false);

        // 用固定词汇定义CountVectorizerModel
        CountVectorizerModel cvm = new CountVectorizerModel(new String[]{"a", "b", "c"})
                .setInputCol("text")
                .setOutputCol("feature");
        System.out.println("使用固定词汇结果");
        cvm.transform(df).show(false);
    }

    private static void featureHasher(SparkSession sparkSession) {
        List<Row> data = Arrays.asList(
                RowFactory.create(2.2, true, "1", "foo"),
                RowFactory.create(3.3, false, "2", "bar"),
                RowFactory.create(4.4, false, "3", "baz"),
                RowFactory.create(5.5, false, "4", "foo")
        );

        StructType schema = new StructType(new StructField[]{
                new StructField("real", DataTypes.DoubleType, false, Metadata.empty()),
                new StructField("bool", DataTypes.BooleanType, false, Metadata.empty()),
                new StructField("stringNum", DataTypes.StringType, false, Metadata.empty()),
                new StructField("string", DataTypes.StringType, false, Metadata.empty())
        });

        Dataset<Row> dataset = sparkSession.createDataFrame(data, schema);

        FeatureHasher hasher = new FeatureHasher()
                .setInputCols(new String[]{"real", "bool", "stringNum", "string"})
                .setOutputCol("features");

        Dataset<Row> featurized = hasher.transform(dataset);
        featurized.show(false);
    }
}
