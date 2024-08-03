package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeEnum {
    /* 新增 */
    CREATE("C", "Add"),
    /* 编辑 */
    UPDATE("U", "Update"),
    /* 删除 */
    DELETE("D", "Delete"),
    /* 排序 */
    SORT("S", "Ordering"),
    /* 切换display */
    DISPLAY("V", "DisplayChange");

    private final String value;
    private final String desc;

    public static String[] allValues() {
        String[] values = new String[TypeEnum.values().length];
        for (int i = 0; i < TypeEnum.values().length; i++) {
            values[i] = TypeEnum.values()[i].getValue();
        }
        return values;
    }
}
