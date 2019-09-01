package ru.nsu.vtimofeev.GameFramework.MainClasses;

import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;


public class FileIO {
    AssetManager assets;

    public FileIO(AssetManager assets) {
        this.assets = assets;
    }

    public InputStream readAsset(String fileName) throws IOException {
        return assets.open(fileName);
    }

}
