/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mathbib;

/**
 *
 * @author Julian
 */
public abstract class VectorSpaceElement extends MathObject {

    public abstract VectorSpaceElement add(VectorSpaceElement vse);

    public abstract VectorSpaceElement subtract(VectorSpaceElement vse);

    public abstract VectorSpaceElement multiply(VectorSpaceElement vse);
}
