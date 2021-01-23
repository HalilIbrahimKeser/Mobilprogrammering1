package com.example.lab2_1_bondesjakk;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ModelBondesjakk extends MainActivityBondesjakk {
    String strtvA = super.tvA.getText().toString();
    String strtvB = super.tvB.getText().toString();
    String strtvC = super.tvC.getText().toString();
    String strtvD = super.tvD.getText().toString();
    String strtvE = super.tvE.getText().toString();
    String strtvF = super.tvF.getText().toString();
    String strtvG = super.tvG.getText().toString();
    String strtvH = super.tvH.getText().toString();
    String strtvK = super.tvK.getText().toString();
    int [][] arr;


    public boolean vunnet() {
        return sjekkRader() || sjekkDiagonaler() || sjekkKolonner();
    }

    public boolean sjekkRader() {
        if (strtvA.equals(strtvB) && strtvA.equals(strtvC)) {
            return true;
        }else if (strtvD.equals(strtvE) && strtvD.equals(strtvF)) {
            return true;
        }else return strtvG.equals(strtvH) && strtvG.equals(strtvK);
    }

    public boolean sjekkKolonner() {
        if (strtvA.equals(strtvD) && strtvA.equals(strtvG)) {
            return true;
        }else if (strtvB.equals(strtvE) && strtvB.equals(strtvH)) {
            return true;
        }else return strtvC.equals(strtvF) && strtvC.equals(strtvK);
    }

    public boolean sjekkDiagonaler() {
        return strtvA.equals(strtvE) && strtvA.equals(strtvK);
    }














}
