package com.example.myapplication.utils;

import android.text.Editable;
import android.text.TextWatcher;

public abstract class TextChangedListener<T> implements TextWatcher {
    // Нужен, чтобы убрать ненужные мне методы beforeTextChanged и onTextChanged.
    // https://stackoverflow.com/questions/11134144/android-edittext-onchange-listener

    private final T target;

    public TextChangedListener(T target) { this.target = target; }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public void afterTextChanged(Editable s) { this.onTextChanged(target, s); }

    public abstract void onTextChanged(T target, Editable s);
}