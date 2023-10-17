package com.examp.gridads;

public class DataModel {
    private TypeInter type;
    private int images;

    public DataModel(TypeInter type) {
        this.type = type;
    }

    public DataModel(TypeInter typess, int src) {
        this.type = typess;
        images = src;
    }

    public int getImages() {
        return images;
    }

    public TypeInter getType() {
        return type;
    }

}

