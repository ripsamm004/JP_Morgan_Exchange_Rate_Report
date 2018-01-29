package com.jpmorgan.model;

import com.jpmorgan.utils.Common;

public enum InstructionTypeEnum {

    UNKNOWN(0, 'X', "unkonwn"),
    BUY(1, 'B', "outgoing"),
    SELL(2, 'B', "incoming");

    private Integer id;
    private Character flag;
    private String flagValue;

    /**
     * Creates a value type
     *
     * @param id Numeric value of the value type
     */
    InstructionTypeEnum(Integer id, Character flag, String flagValue) {
        this.id = id;
        this.flag = flag;
        this.flagValue = flagValue;
    }

    /**
     * Returns the value type for the specified string id of value type
     * Always returns an object
     *
     * @param id Numeric value of the value type
     * @return model.InstructionTypeEnum
     */
    public static InstructionTypeEnum getById(String id) {
        return Common.isBlank(id) ? UNKNOWN : get(Common.parseInt(id));
    }

    /**
     * Returns the data type for the specified numeric value
     * Always returns an object
     *
     * @param id Numeric value of the instruction type
     * @return model.InstructionTypeEnum
     */
    public static InstructionTypeEnum get(Integer id) {
        if (id == null) {
            return UNKNOWN;
        } else {
            for (InstructionTypeEnum instructionTypeEnum : values()) {
                if (instructionTypeEnum.id != null && instructionTypeEnum.id.intValue() == id.intValue()) {
                    return instructionTypeEnum;
                }
            }
        }
        return UNKNOWN;
    }

    /**
     * Returns the numeric value of the data type - can be null
     *
     * @return Numeric value
     */
    public Integer getId() {
        return id;
    }

    /**
     * Returns the character value of the data type - can be null
     *
     * @return Character flag
     */
    public Character getFlag() {
        return flag;
    }

    /**
     * Returns the string value of the data type - can be null
     *
     * @return String flagValue
     */
    public String getFlagValue() {
        return flagValue;
    }


    /**
     * Returns true if the data type is the same as this id
     *
     * @param valueType Value Type or null
     * @return True if the values are equivalent
     */
    public boolean is(InstructionTypeEnum valueType) {
        if (valueType == null &&  this.id == null) {
            return true;
        } else if (valueType != null && valueType.id == null && this.id == null) {
            return true;
        } else if (valueType != null && valueType.id != null && this.id != null && valueType.id.equals(id)) {
            return true;
        } else {
            return false;
        }
    }


}
