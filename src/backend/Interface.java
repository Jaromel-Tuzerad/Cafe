package backend;

import customExceptions.GUIAlertException;

public interface Interface {
    float getStoredAmount();
    void addToStorage(float amount);
    void takeFromStorage(float amount) throws GUIAlertException;
}