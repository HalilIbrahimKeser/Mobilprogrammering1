package com.example.lab2_1_bondesjakk;

public class ModelBondesjakk extends MainActivityBondesjakk {
    public String strA;
    public String strB;
    public String strC;
    public String strD;
    public String strE;
    public String strF;
    public String strG;
    public String strH;
    public String strK;

    public void spillVunnetOgFerdig() {
        strA = tvA.getText().toString();
        strB = tvB.getText().toString();
        strC = tvC.getText().toString();
        strD = tvD.getText().toString();
        strE = tvE.getText().toString();
        strF = tvF.getText().toString();
        strG = tvG.getText().toString();
        strH = tvH.getText().toString();
        strK = tvK.getText().toString();


        this.booleanSpillerVunnet = sjekkRader() || sjekkDiagonaler() || sjekkKolonner();
    }

    public boolean sjekkRader() {
        if (strA.equals(strB) && strA.equals(strC) && !strA.isEmpty()) {
            return true;
        } else if (strD.equals(strE) && strD.equals(strF) && !strD.isEmpty()) {
            return true;
        } else return strG.equals(strH) && strG.equals(strK) && !strG.isEmpty();
    }

    public boolean sjekkKolonner() {
        if (strA.equals(strD) && strA.equals(strG) && !strA.isEmpty()) {
            return true;
        }else if (strB.equals(strE) && strB.equals(strH) && !strB.isEmpty()) {
            return true;
        }else return strC.equals(strF) && strC.equals(strK) && !strC.isEmpty();
    }

    public boolean sjekkDiagonaler() {
        if (strA.equals(strE) && strA.equals(strK) && !strA.isEmpty()) {
            return true;
        }else return strC.equals(strE) && strC.equals(strG) && !strC.isEmpty();
    }













}
