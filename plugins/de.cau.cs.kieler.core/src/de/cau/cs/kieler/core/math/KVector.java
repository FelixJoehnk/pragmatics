/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.math;

import java.util.StringTokenizer;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.util.IDataObject;

/**
 * A simple 2D vector class which supports translation, scaling, normalization etc.
 * 
 * @author uru
 * @author owo
 */
public class KVector implements IDataObject {

    // CHECKSTYLEOFF VisibilityModifier
    /** x coordinate. */
    public double x;
    /** y coordinate. */
    public double y;
    // CHECKSTYLEON VisibilityModifier

    /** one full turn in a circle in degrees (360°). */
    public static final double FULL_CIRCLE = 360;

    /**
     * Create vector with default coordinates (0,0).
     */
    public KVector() {
        this.x = 0.0;
        this.y = 0.0;
    }

    /**
     * Constructs a new vector from given values.
     * 
     * @param thex
     *            x value
     * @param they
     *            y value
     */
    public KVector(final double thex, final double they) {
        this.x = thex;
        this.y = they;
    }

    /**
     * Creates an exact copy of a given vector v.
     * 
     * @param v
     *            existing vector
     */
    public KVector(final KVector v) {
        this.x = v.x;
        this.y = v.y;
    }

    /**
     * Creates a normalized vector for the passed angle in degree.
     * 
     * @param alpha
     *            angle in [0, 360)
     */
    public KVector(final double alpha) {
        if (alpha < 0 || alpha >= FULL_CIRCLE) {
            throw new IllegalArgumentException(
                    "Value for angle has to be within [0, 360)! Given Value: " + alpha);
        }

        this.x = Math.sin(Math.toRadians(alpha));
        this.y = Math.cos(Math.toRadians(alpha));
    }

    /**
     * returns an exact copy of this vector.
     * 
     * @return identical vector
     */
    @Override
    public KVector clone() {
        return new KVector(this.x, this.y);
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(final Object obj) {
        if (obj instanceof KVector) {
            KVector other = (KVector) obj;
            return this.x == other.x && this.y == other.y;
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return Double.valueOf(x).hashCode() + Double.valueOf(y).hashCode();
    }

    /**
     * Compute euclidean norm (a.k.a length). FIXME why define two names for the same function?
     * (msp)
     * 
     * @deprecated use getLength()
     * @return length of this vector
     */
    public final double getNorm() {
        return getLength();
    }

    /**
     * returns this vector's length.
     * 
     * @return Math.sqrt(x*x + y*y)
     */
    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * returns square length of this vector.
     * 
     * @return x*x + y*y
     */
    public double getSquareLength() {
        return x * x + y * y;
    }

    /**
     * Set vector to (0,0).
     * 
     * @return <code>this</code>
     */
    public final KVector reset() {
        this.x = 0.0;
        this.y = 0.0;
        return this;
    }

    /**
     * Vector addition.
     * 
     * @param v
     *            vector to add
     * @return <code>this + v</code>
     */
    public final KVector add(final KVector v) {
        this.x += v.x;
        this.y += v.y;
        return this;
    }

    /**
     * Returns the sum of two given vectors as a new vector instance.
     * 
     * @param v1
     *            first vector
     * @param v2
     *            second vector
     * @return new vector first + second
     */
    public static KVector add(final KVector v1, final KVector v2) {
        return new KVector(v1.x + v2.x, v1.y + v2.y);
    }

    /**
     * Vector subtraction.
     * 
     * @param v
     *            vector to subtract
     * @return <code>this</code>
     */
    public final KVector sub(final KVector v) {
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }

    /**
     * Returns the subtraction of the two given vectors as a new vector instance.
     * 
     * @param v1
     *            first vector
     * @param v2
     *            second vector
     * @return new vector first - second
     */
    public static KVector sub(final KVector v1, final KVector v2) {
        return new KVector(v1.x - v2.x, v1.y - v2.y);
    }

    /**
     * Scale the vector.
     * 
     * @param scale
     *            scaling factor
     * @return <code>this</code>
     */
    public final KVector scale(final double scale) {
        this.x *= scale;
        this.y *= scale;
        return this;
    }

    /**
     * Normalize the vector.
     * 
     * @return <code>this</code>
     */
    public KVector normalize() {
        double length = this.getLength();
        this.x = this.x / length;
        this.y = this.y / length;
        return this;
    }

    /**
     * scales this vector to the passed length.
     * 
     * @param length
     *            length to scale to
     * @return <code>this</code>
     */
    public KVector scaleToLength(final double length) {
        this.normalize();
        this.scale(length);
        return this;
    }

    /**
     * Negate the vector.
     * 
     * @return <code>this</code>
     */
    public KVector negate() {
        this.x = -this.x;
        this.y = -this.y;
        return this;
    }

    /**
     * Returns degree representation of this vector in degree.
     * 
     * @return value within [0,360)
     */
    public double toDegrees() {
        KVector temp = this.clone();
        temp.normalize();
        double sin = Math.toDegrees(Math.asin(temp.x));
        double cos = Math.toDegrees(Math.acos(temp.y));

        if (y < 0 && x < 0) {
            return (FULL_CIRCLE / 2) - sin;
        } else if (y < 0) {
            return cos;
        } else if (x < 0) {
            return FULL_CIRCLE + sin;
        } else {
            return cos;
        }
    }

    /**
     * Add some "noise" to this vector.
     */
    public final void wiggle() {
        final double theWiggle = 0.5;
        this.x += (Math.random() - theWiggle);
        this.y += (Math.random() - theWiggle);
    }

    /**
     * Create a scaled version of this vector.
     * 
     * @param lambda
     *            scaling factor
     * @return new vector which is <code>this</code> scaled by <code>lambda</code>
     */
    public final KVector scaledCreate(final double lambda) {
        return new KVector(this).scale(lambda);
    }

    /**
     * Create a normalized version of this vector.
     * 
     * @return normalized copy of <code>this</code>
     */
    public final KVector normalizedCreate() {
        return new KVector(this).normalize();
    }

    /**
     * Create a sum from this vector and another vector.
     * 
     * @param v
     *            second addend
     * @return new vector which is the sum of <code>this</code> and <code>v</code>
     */
    public final KVector sumCreate(final KVector v) {
        return new KVector(this).add(v);
    }

    /**
     * Create a sum from this vector and another vector.
     * 
     * @param v
     *            subtrahend
     * @return new vector which is the difference between <code>this</code> and <code>v</code>
     */
    public final KVector differenceCreate(final KVector v) {
        return new KVector(this).sub(v);
    }

    /**
     * Returns the distance between two vectors.
     * 
     * @param v2
     *            second vector
     * @return distance between this and second vector
     */
    public double distance(final KVector v2) {
        double dx = this.x - v2.x;
        double dy = this.y - v2.y;
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * Returns the distance between two vectors.
     * 
     * @param v1
     *            first vector
     * @param v2
     *            second vector
     * @return distance between first and second
     */
    public static double distance(final KVector v1, final KVector v2) {
        double dx = v1.x - v2.x;
        double dy = v1.y - v2.y;
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * Returns the dot product of the two given vectors.
     * 
     * @param v2
     *            second vector
     * @return (this.x * this.x) + (v1.y * v2.y)
     */
    public double productDot(final KVector v2) {
        return ((this.x * v2.x) + (this.y * v2.y));
    }

    /**
     * Returns the dot product of the two given vectors.
     * 
     * @param v1
     *            first vector
     * @param v2
     *            second vector
     * @return (this.x * this.x) + (v1.y * v2.y)
     */
    public static double productDot(final KVector v1, final KVector v2) {
        return ((v1.x * v2.x) + (v1.y * v2.y));
    }

    /**
     * {@inheritDoc}
     */
    public void parse(final String string) throws KielerException {
        StringTokenizer tokenizer = new StringTokenizer(string, ",;()[]{} \t\n");
        x = 0;
        y = 0;
        try {
            if (tokenizer.hasMoreTokens()) {
                x = Double.parseDouble(tokenizer.nextToken());
            }
            if (tokenizer.hasMoreTokens()) {
                y = Double.parseDouble(tokenizer.nextToken());
            }
        } catch (NumberFormatException exception) {
            throw new KielerException(
                    "The given string does not match the expected format for vectors." + exception);
        }
    }
    
}
