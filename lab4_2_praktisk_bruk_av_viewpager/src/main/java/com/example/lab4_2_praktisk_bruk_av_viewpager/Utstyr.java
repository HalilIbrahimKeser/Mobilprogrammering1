package com.example.lab4_2_praktisk_bruk_av_viewpager;

public class Utstyr {
    private String type;
    private String produsent;
    private String modell;
    private long innkjopt;
    private char status; // (utlånt eller ikke)
    private String utlant_til;
    private String bildeUrl;

    public Utstyr(String type, String produsent, String modell, long innkjopt, char status, String utlant_til, String bildeUrl) {
        this.type = type;
        this.produsent = produsent;
        this.modell = modell;
        this.innkjopt = innkjopt;
        this.status = status;
        this.utlant_til = utlant_til;
        this.bildeUrl = bildeUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProdusent() {
        return produsent;
    }

    public void setProdusent(String produsent) {
        this.produsent = produsent;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public long getInnkjopt() {
        return innkjopt;
    }

    public void setInnkjopt(long innkjopt) {
        this.innkjopt = innkjopt;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public String getUtlant_til() {
        return utlant_til;
    }

    public void setUtlant_til(String utlant_til) {
        this.utlant_til = utlant_til;
    }

    public String getBildeUrl() {
        return bildeUrl;
    }

    public void setBildeUrl(String bildeUrl) {
        this.bildeUrl = bildeUrl;
    }

    @Override
    public String toString() {
        return "Utstyr{" +
                "type='" + type + '\'' +
                ", produsent='" + produsent + '\'' +
                ", modell='" + modell + '\'' +
                ", innkjøpt=" + innkjopt +
                ", status=" + status +
                ", utlånt_til='" + utlant_til + '\'' +
                ", bildeUrl='" + bildeUrl + '\'' +
                '}';
    }
}
