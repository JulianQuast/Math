/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mathbib;

/**
 *
 * @author Julian C. Quast (c) 2013
 * @deprecated 
 */
public class Expression extends MathObject {

    Expression[] children = new Expression[0];
    ExpressionType type = ExpressionType.NONE;
    String stringcontent = "";
    ExtDecimal numericalcontent;
    Operation operationalcontent;

    public enum ExpressionType {

        NONE, NUMBER, VARIABLE, OPERATION
    };

    public enum Operation {

        ADD {

            @Override
            public String toString() {
                return "+";
            }
        }, SUBTRACT {

            @Override
            public String toString() {
                return "-";
            }
        }, NEGATE {

            @Override
            public String toString() {
                return "-";
            }
        }, MULTIPLY {

            @Override
            public String toString() {
                return "*";
            }
        }, DIVIDE {

            @Override
            public String toString() {
                return "/";
            }
        }, SQRT {

            @Override
            public String toString() {
                return "sqrt ";
            }
        }, POW {

            @Override
            public String toString() {
                return "^";
            }
        }, EXP {

            @Override
            public String toString() {
                return "exp ";
            }
        }, SUM {

            @Override
            public String toString() {
                return "sum ";
            }
        }, PRODUCT {

            @Override
            public String toString() {
                return "prod ";
            }
        }
    };

    public Expression() {
    }

    /**
     * Constructor for a variable
     *
     * @param etype
     * @param scontent
     */
    public Expression(String scontent) {
        type = ExpressionType.VARIABLE;
        stringcontent = scontent;
    }

    /**
     * Constructor for a number
     *
     * @param etype
     * @param scontent
     */
    public Expression(ExtDecimal ncontent) {
        type = ExpressionType.NUMBER;
        numericalcontent = ncontent;
    }

    /**
     * Constructor for an expression
     *
     * @param etype
     * @param scontent
     */
    public Expression(Operation ocontent, Expression ... child) {
        type = ExpressionType.OPERATION;
        children = child;
        operationalcontent = ocontent;
    }

    /**
     * Returns the number of children of {@code this}.
     *
     * @return
     */
    public int getDegree() {
        return children.length;
    }

    /**
     * Returns the evaluated Expression {@code this}
     *
     * @return evaluated form of {@code this}
     */
//    public Expression evaluate() {
//        if (type == ExpressionType.OPERATION) {
//            switch (operationalcontent) {
//                case ADD:
//                    Expression
//                    ExtDecimal value = ExtDecimal.ZERO;
//                    for (int i = 0; i < children.length; i++) {
//                        value = value.add(children[i].evaluate());
//                    }
//            }
//        } else {
//            return this;
//        }
//    }
}
