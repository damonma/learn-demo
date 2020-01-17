package spark.ml;

import org.apache.spark.ml.classification.BinaryLogisticRegressionTrainingSummary;
import org.apache.spark.ml.classification.LogisticRegression;
import org.apache.spark.ml.classification.LogisticRegressionModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;

/**
 * @author damon
 * @version 2018/9/28
 */
public class Classification {

    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder().master("local").appName("mySpark").getOrCreate();
        sparkSession.sparkContext().setLogLevel("WARN");

        binomialLogisticRegression(sparkSession);
    }

    private static void binomialLogisticRegression(SparkSession sparkSession) {
        Dataset<Row> training = sparkSession.read().format("libsvm").load("spark/data/mllib/sample_libsvm_data.txt");
        LogisticRegression lr = new LogisticRegression()
                .setMaxIter(10)
                .setRegParam(3.0)
                .setElasticNetParam(0.8);

        LogisticRegressionModel lrModel = lr.fit(training);
        System.out.println("Coefficients: " + lrModel.coefficients() + " Intercept: " + lrModel.intercept());

        LogisticRegression mlr = new LogisticRegression()
                .setMaxIter(10)
                .setRegParam(0.3)
                .setElasticNetParam(0.8)
                .setFamily("multinomial");

        LogisticRegressionModel mlrModel = mlr.fit(training);
        System.out.println("Multinomial coefficients: " + lrModel.coefficientMatrix() + "\nMultinomial intercepts: "
                + mlrModel.interceptVector());

        BinaryLogisticRegressionTrainingSummary trainingSummary = lrModel.binarySummary();

        double[] objectiveHistory = trainingSummary.objectiveHistory();
        for (double lossPerIteration : objectiveHistory) {
            System.out.println(lossPerIteration);
        }

        Dataset<Row> roc = trainingSummary.roc();
        roc.show();
        roc.select("FPR").show();
        System.out.println(trainingSummary.areaUnderROC());


        Dataset<Row> fMeasure = trainingSummary.fMeasureByThreshold();
        double maxFMeasure = fMeasure.select(functions.max("F-Measure")).head().getDouble(0);
        double bestThreshold = fMeasure.where(fMeasure.col("F-Measure").equalTo(maxFMeasure))
                .select("threshold").head().getDouble(0);
        lrModel.setThreshold(bestThreshold);
    }
}
