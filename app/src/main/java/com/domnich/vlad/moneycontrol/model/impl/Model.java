package com.domnich.vlad.moneycontrol.model.impl;

import com.domnich.vlad.moneycontrol.model.iModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlad on 22.02.2017.
 */

public class Model implements iModel {

    private List<Object> values = new ArrayList<>();

    @Override
    public void add(Object value) {
        values.add(value);
    }

    @Override
    public Object get(int index) {
        return values.get(index);
    }
}
