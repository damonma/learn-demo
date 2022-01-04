/**
 * 构建者模式：将一个复杂的对象构建与它的表示分离，使得同样的构建过程可以创建不同的表示；
 */
package com.damon.designPatterns.builder;

/**
 * IBuilder
 *
 * @author damon
 * @date 2017/4/28
 */
public interface IBuilder {

    public void buildWheel();

    public void buildSkeleton();

    public void buildEngine();

    Car buildCar();
}
