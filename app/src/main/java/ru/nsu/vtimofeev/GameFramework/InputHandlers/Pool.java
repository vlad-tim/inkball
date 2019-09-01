package ru.nsu.vtimofeev.GameFramework.InputHandlers;

import java.util.ArrayList;
import java.util.List;

public class Pool<T> {
    public interface PoolObjectFactory<T> {
        public T createObject();
    }

    private final List<T> freeObjects;
    private final PoolObjectFactory<T> factory;
    private final int maxSize;
    private final int initialSize;

    public Pool(PoolObjectFactory<T> factory, int initialSize, int maxSize) {
        this.factory = factory;
        this.maxSize = maxSize;
        this.initialSize = initialSize;
        this.freeObjects = new ArrayList<T>(initialSize);
        
        initInitial();
    }

    private void initInitial() {
        
        for (int i = 0; i < initialSize; ++i) {
            freeObjects.add(factory.createObject());
        }
    }

    public T acquire() {
        T object = null;

        if (freeObjects.size() == 0) {
            object = factory.createObject();
        } else {
            object = freeObjects.remove(freeObjects.size() - 1);
        }

        return object;
    }

    public void release(T object) {
        if (freeObjects.size() < maxSize) {
            freeObjects.add(object);
        }
    }

    public int getSize() {
        return freeObjects.size();
    }
}
