package de.ykstr.jneuralnet.components.neurons;

import de.ykstr.jneuralnet.functions.IActivationFunction;

public abstract class Neuron {

    protected double value;
    protected boolean isInputCurrent = false;

    protected IActivationFunction activationFunction;
    protected double cachedActivationValue;
    protected boolean isCacheCurrent = false;

    public Neuron(IActivationFunction f){
        setActivationFunction(f);
    }

    public double calculate() {
        if(!isInputCurrent){
            setValue(calculateValue());
            setInputCurrent(true);
            setCacheCurrent(false);
        }
        if(!isCacheCurrent){
            setCachedActivationValue(activationFunction.calculate(value));
            setCacheCurrent(true);
        }
        return cachedActivationValue;
    }

    public double calculateValue() {
        return value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
        setInputCurrent(false);
    }

    public boolean isInputCurrent() {
        return isInputCurrent;
    }

    public void setInputCurrent(boolean inputCurrent) {
        isInputCurrent = inputCurrent;
    }

    public IActivationFunction getActivationFunction() {
        return activationFunction;
    }

    public void setActivationFunction(IActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
        setCacheCurrent(false);
    }

    public double getCachedActivationValue() {
        return cachedActivationValue;
    }

    public void setCachedActivationValue(double cachedActivationValue) {
        this.cachedActivationValue = cachedActivationValue;
    }

    public boolean isCacheCurrent() {
        return isCacheCurrent;
    }

    public void setCacheCurrent(boolean cacheCurrent) {
        isCacheCurrent = cacheCurrent;
    }
}
