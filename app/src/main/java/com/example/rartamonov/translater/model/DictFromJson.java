package com.example.rartamonov.translater.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DictFromJson { // Разбор JSON словарной статьи

    @SerializedName("head")
    @Expose
    private Head head;

    @SerializedName("def")
    @Expose
    private List<Def> def = null;

    @SerializedName("code")
    @Expose
    private Integer code;

    @SerializedName("message")
    @Expose
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    private List<Def> getDef() {
        return def;
    }

    public void setDef(List<Def> def) {
        this.def = def;
    }

    private class Def {

        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("pos")
        @Expose
        private String pos;
        @SerializedName("ts")
        @Expose
        private String ts;
        @SerializedName("fl")
        @Expose
        private String fl;
        @SerializedName("tr")
        @Expose
        private List<Tr> tr = null;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        private String getPos() {
            return pos;
        }

        public void setPos(String pos) {
            this.pos = pos;
        }

        private String getTs() {
            return ts;
        }

        public void setTs(String ts) {
            this.ts = ts;
        }

        private String getFl() {
            return fl;
        }

        public void setFl(String fl) {
            this.fl = fl;
        }

        public List<Tr> getTr() {
            return tr;
        }

        public void setTr(List<Tr> tr) {
            this.tr = tr;
        }

    }

    private class Ex {

        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("tr")
        @Expose
        private List<Tr_> tr = null;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public List<Tr_> getTr() {
            return tr;
        }

        public void setTr(List<Tr_> tr) {
            this.tr = tr;
        }

    }

    private class Head {


    }

    private class Mean {

        @SerializedName("text")
        @Expose
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

    }

    private class Syn {

        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("pos")
        @Expose
        private String pos;
        @SerializedName("asp")
        @Expose
        private String asp;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getPos() {
            return pos;
        }

        public void setPos(String pos) {
            this.pos = pos;
        }

        public String getAsp() {
            return asp;
        }

        public void setAsp(String asp) {
            this.asp = asp;
        }

    }

    private class Tr {

        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("pos")
        @Expose
        private String pos;
        @SerializedName("asp")
        @Expose
        private String asp;
        @SerializedName("syn")
        @Expose
        private List<Syn> syn = null;
        @SerializedName("mean")
        @Expose
        private List<Mean> mean = null;
        @SerializedName("ex")
        @Expose
        private List<Ex> ex = null;
        @SerializedName("gen")
        @Expose
        private String gen;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getPos() {
            return pos;
        }

        public void setPos(String pos) {
            this.pos = pos;
        }

        public String getAsp() {
            return asp;
        }

        public void setAsp(String asp) {
            this.asp = asp;
        }

        private List<Syn> getSyn() {
            return syn;
        }

        public void setSyn(List<Syn> syn) {
            this.syn = syn;
        }

        private List<Mean> getMean() {
            return mean;
        }

        public void setMean(List<Mean> mean) {
            this.mean = mean;
        }

        private List<Ex> getEx() {
            return ex;
        }

        public void setEx(List<Ex> ex) {
            this.ex = ex;
        }

        private String getGen() {
            return gen;
        }

        public void setGen(String gen) {
            this.gen = gen;
        }

    }

    private class Tr_ {

        @SerializedName("text")
        @Expose
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

    }

    public StringBuffer getText(DictFromJson dictFromJson){

        StringBuffer result = new StringBuffer("");

        for (Def rowDef:dictFromJson.getDef()){
            result.append(rowDef.getText());
            if(!(rowDef.getTs()==null)) {
                result.append("<font color=\"#808080\"> [").append(rowDef.getTs()).append("] </font>");
            }else{
                result.append(" ");
            }
            if(!(rowDef.getPos()==null)) {
                result.append("<font color=\"#008000\">").append(rowDef.getPos()).append("</font> ");
            }
            if (!(rowDef.getFl()==null)) {
                result.append("<font color=\"#808080\">").append(rowDef.getFl()).append("</font>");
            }
            result.append("<br></br>");

            int i=1;
            for (Tr rowTr: rowDef.getTr()){
                result.append("<font color=\"#808080\">").append(String.valueOf(i)).append("</font> <font color=\"#4169E1\">").append(rowTr.getText()).append("</font>");

                if (!(rowTr.getSyn() == null)) {
                    if (!rowTr.getSyn().isEmpty()) {
                        result.append("<font color=\"#4169E1\">").append(", ");

                        for (Syn rowSyn : rowTr.getSyn()) {
                            result.append(rowSyn.getText()).append(", ");
                        }

                        result.setLength(result.length() - 2);
                        result.append("</font>");
                    }
                } else if (!(rowTr.getGen() == null)) {
                    result.append("<font color=\"#808080\"> ").append(rowTr.getGen()).append("</font>");
                }

                if(!(rowTr.getMean()==null)) if (!rowTr.getMean().isEmpty()){
                    result.append("<br></br><font color=\"#8B3E2F\">(");
                    for (Mean rowMean:rowTr.getMean()){
                        result.append(rowMean.getText()).append(",");
                    }
                    result.setLength(result.length()-1);
                    result.append(")</font>");
                }

                if(!(rowTr.getEx() == null)) {
                    if (!rowTr.getEx().isEmpty()) {
                        result.append("<br></br><font color=\"#808080\">");
                        for (Ex rowEx : rowTr.getEx()) {
                            result.append("&nbsp;&nbsp;&nbsp;&nbsp;").append(rowEx.getText());
                            if (!(rowEx.getTr() == null)) if (!rowEx.getTr().isEmpty()) {
                                result.append("-");
                                for (Tr_ rowTr_ : rowEx.getTr()) {
                                    result.append(rowTr_.getText());
                                }
                            }
                            result.append("<br></br>");
                        }
                        result.append("</font>");
                    }
                } else result.append("<br></br>");

                i++;
                result.append("<br></br>");
            }
        }

        return result;
    }

}
