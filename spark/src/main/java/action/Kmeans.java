package action;

import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.linalg.distributed.RowMatrix;
import org.apache.spark.mllib.recommendation.ALS;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.mllib.recommendation.Rating;
import org.apache.spark.mllib.stat.MultivariateStatisticalSummary;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

/**
 * @author damon
 * @version 2018/10/20
 */
public class Kmeans {
    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder().master("local").appName("mySpark").getOrCreate();
        sparkSession.sparkContext().setLogLevel("WARN");

        // 加载电影信息
        RDD<String> fileItem = sparkSession.sparkContext().textFile("spark/data/action/ml-100k/u.item", 0);
        System.out.println(fileItem.first());

        // 加载电影类别信息
        RDD<String> fileGenre = sparkSession.sparkContext().textFile("spark/data/action/ml-100k/u.genre", 0);
        System.out.println(fileGenre.first());

        // 加载评论人的信息
        RDD<String> fileUser = sparkSession.sparkContext().textFile("spark/data/action/ml-100k/u.user", 0);
        // 加载评论人的评论信息
        RDD<String> fileData = sparkSession.sparkContext().textFile("spark/data/action/ml-100k/u.data", 0);

        // 训练推荐模型
        RDD<Rating> dataVector = fileData.toJavaRDD().map(d -> d.split("\t")).map(x -> new Rating(Integer.parseInt
                (x[0]), Integer.parseInt(x[1]), Double.parseDouble(x[2]))).rdd().cache();
        MatrixFactorizationModel aslModel = ALS.train(dataVector, 50, 10, 0.1);

        // 获取用户相似特征
        RDD<Tuple2<Object, double[]>> userFactors = aslModel.userFeatures();

        // 用户特征向量化
        RDD<Vector> userVectors = userFactors.toJavaRDD().map(x -> Vectors.dense(x._2)).rdd();

        // 获取商品相似特征
        RDD<Tuple2<Object, double[]>> movieFactors = aslModel.productFeatures();

        // 商品相似特征向量化
        RDD<Vector> movieVectors = movieFactors.toJavaRDD().map(x -> Vectors.dense(x._2)).rdd();

        // 商品向量归一化判断
        RowMatrix movieMatrix = new RowMatrix(movieVectors);
        MultivariateStatisticalSummary movieMatrixSummary = movieMatrix.computeColumnSummaryStatistics();
        System.out.println(movieMatrixSummary.mean()); // 每列的平均值
        System.out.println(movieMatrixSummary.variance()); // 每列的方差

        // 用户向量归一化判断
        RowMatrix userMatrix = new RowMatrix(userVectors);
        MultivariateStatisticalSummary userMatrixSummary=userMatrix.computeColumnSummaryStatistics();
        System.out.println(userMatrixSummary.mean()); // 每列的平均值
        System.out.println(userMatrixSummary.variance()); // 每列的方差
    }
}
