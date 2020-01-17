package spark.ml;

import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.classification.LogisticRegression;
import org.apache.spark.ml.classification.LogisticRegressionModel;
import org.apache.spark.ml.feature.HashingTF;
import org.apache.spark.ml.feature.Tokenizer;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.linalg.Vectors;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.util.Arrays;
import java.util.List;

/**
 * @author damon
 * @version 2018/9/11
 */
public class Pipelines {

    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder().master("local").appName("mySpark").getOrCreate();
        sparkSession.sparkContext().setLogLevel("WARN");
        pipelineComponents(sparkSession);
    }

    private static void pipelineComponents(SparkSession sparkSession) {
        // 训练数据.
        List<Row> dataTraining = Arrays.asList(
                RowFactory.create(1.0, Vectors.dense(0.0, 1.1, 0.1)),
                RowFactory.create(0.0, Vectors.dense(2.0, 1.0, -1.0)),
                RowFactory.create(0.0, Vectors.dense(2.0, 1.3, 1.0)),
                RowFactory.create(1.0, Vectors.dense(0.0, 1.2, -0.5))
        );

        StructType schema = new StructType(new StructField[]{
                new StructField("label", DataTypes.DoubleType, false, Metadata.empty()),
                new StructField("features", new VectorUDT(), false, Metadata.empty())
        });

        Dataset<Row> training = sparkSession.createDataFrame(dataTraining, schema);

        // 创建一个LogisticRegression实例，它是一个Estimator
        LogisticRegression lr = new LogisticRegression();
        // 打印参数、文档和默认值
        System.out.println("LogisticRegression parameters:\n" + lr.explainParams() + "\n");

        // 用setter方法设置参数
        lr.setMaxIter(10).setRegParam(0.01);

        // 学习LogisticRegression模型，使用存储在lr中的参数
        LogisticRegressionModel model1 = lr.fit(training);

        // 由于model1是一个Model（即由Estimator产生的Transformer）
        // 可以查看在fit()中使用的参数
        // 这将打印参数(名称:值)对，其中名称是这个LogisticRegression实例的惟一id
        System.out.println("Model 1 was fit using parameters: " + model1.parent().explainParams());

        // 也可以使用ParamMap来指定参数
        ParamMap paramMap = new ParamMap()
                .put(lr.maxIter().w(20)) // 指定1个参数
                .put(lr.maxIter(), 30) // 覆盖原来的maxIter
                .put(lr.regParam().w(0.1), lr.threshold().w(0.55) // 指定多个参数
                );

        // 也可以组合ParamMap
        ParamMap paramMap2 = new ParamMap()
                .put(lr.probabilityCol().w("myProbability"));  // 改变输出列名
        ParamMap paramMapCombined = paramMap.$plus$plus(paramMap2);

        // 学习用paramMapCombined参数创建新模型
        // 使用paramMapCombined覆盖前面通过setter方法设置的所有参数
        LogisticRegressionModel model2 = lr.fit(training, paramMapCombined);
        System.out.println("Model 2 was fit using parameters: " + model2.parent().explainParams());

        // 测试文档数据
        List<Row> dataTest = Arrays.asList(
                RowFactory.create(1.0, Vectors.dense(-1.0, 1.5, 1.3)),
                RowFactory.create(0.0, Vectors.dense(3.0, 2.0, -0.1)),
                RowFactory.create(1.0, Vectors.dense(0.0, 2.2, -1.5))
        );
        Dataset<Row> test = sparkSession.createDataFrame(dataTest, schema);

        // 使用Transformer.transform()方法对测试文档进行预测
        // LogisticRegression.transform 只会使用features列
        // 注意，model2.transform()输出一个“myProbability”列，而不是通常的“probability”列，因为重新命名了lr.probabilityCol参数
        Dataset<Row> result = model2.transform(test);
        Dataset<Row> rows = result.select("features", "label", "myProbability", "prediction");
        for (Row r : rows.collectAsList()) {
            System.out.println("(" + r.get(0) + ", " + r.get(1) + ") -> prob=" + r.get(2) + ", prediction=" + r.get(3));
        }
    }

    private static void pipeline(SparkSession sparkSession) {
        // 准备训练文档数据，并设置标签
//        Dataset<Row> training = sparkSession.createDataFrame(Arrays.asList(
//                new JavaLabeledDocument(0L, "a b c d e spark", 1.0),
//                new JavaLabeledDocument(1L, "b d", 0.0),
//                new JavaLabeledDocument(2L, "spark f g h", 1.0),
//                new JavaLabeledDocument(3L, "hadoop mapreduce", 0.0)
//        ), JavaLabeledDocument.class);

        // 配置一个ML Pipeline，由三个阶段组成:tokenizer、hashingTF和lr
        Tokenizer tokenizer = new Tokenizer()
                .setInputCol("text")
                .setOutputCol("words");
        HashingTF hashingTF = new HashingTF()
                .setNumFeatures(1000)
                .setInputCol(tokenizer.getOutputCol())
                .setOutputCol("features");
        LogisticRegression lr = new LogisticRegression()
                .setMaxIter(10)
                .setRegParam(0.001);
        Pipeline pipeline = new Pipeline()
                .setStages(new PipelineStage[]{tokenizer, hashingTF, lr});

        // 适配训练数据
//        PipelineModel pipelineModel = pipeline.fit(training);
//
//        // 准备测试文档数据，无需设置标签
//        Dataset<Row> test = sparkSession.createDataFrame(Arrays.asList(
//                new JavaDocument(4L, "spark i j k"),
//                new JavaDocument(5L, "l m n"),
//                new JavaDocument(6L, "spark hadoop spark"),
//                new JavaDocument(7L, "apache hadoop")
//        ), JavaDocument.class);

        // 对测试文档数据进行预测
//        Dataset<Row> predictions = pipelineModel.transform(test);
//        for (Row r : predictions.select("id", "text", "probability", "prediction").collectAsList()) {
//            System.out.println("(" + r.get(0) + ", " + r.get(1) + ") --> prob=" + r.get(2) + ", prediction=" + r.get(3));
//        }
    }
}
