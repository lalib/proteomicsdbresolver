package com.bilalalp.proteomicsdbresolver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProteotypicityDto implements Serializable {

    @JsonProperty("__metadata")
    private MetaData metaData;

    @JsonProperty("UNIQUE_IDENTIFIER")
    private String uniqueIdentifier;

    @JsonProperty("RANK_ORDER")
    private String rankOrder;

    @JsonProperty("PEPTIDE_ID")
    private String peptideId;

    @JsonProperty("PSMS")
    private String psms;

    @JsonProperty("OCCURRENCE")
    private String occurence;

    @JsonProperty("PROTEOTYPICITY")
    private String proteoTypicity;

    @JsonProperty("IDENTIFIER_COUNT")
    private String identifierCount;

    @JsonProperty("UNIQUENESS")
    private String uniqueness;

    @JsonProperty("MAX_MASCOT_SCORE")
    private String maxMascotScore;

    @JsonProperty("SEQUENCE")
    private String sequence;

    public static String getHeaders() {
        return "UNIQUE_IDENTIFIER" + "," + "RANK_ORDER" + "," + "PEPTIDE_ID" + "," + "PSMS" + "," + "OCCURRENCE" + "," + "PROTEOTYPICITY" +
                "," + "IDENTIFIER_COUNT" + "," + "UNIQUENESS" + "," + "MAX_MASCOT_SCORE" + "," + "SEQUENCE";
    }

    @Override
    public String toString() {
        return getFieldWithNullCheck(uniqueIdentifier) + "," + getFieldWithNullCheck(rankOrder) +
                "," + getFieldWithNullCheck(peptideId) + "," + getFieldWithNullCheck(psms) + "," +
                getFieldWithNullCheck(occurence) + "," + getFieldWithNullCheck(proteoTypicity) + "," + getFieldWithNullCheck(identifierCount) +
                "," + getFieldWithNullCheck(uniqueness) + "," + getFieldWithNullCheck(maxMascotScore) + "," + getFieldWithNullCheck(sequence);
    }

    private String getFieldWithNullCheck(final String field) {
        if (field != null && !field.isEmpty()) {
            return field;
        } else {
            return "";
        }
    }
}