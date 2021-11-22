package com.zyg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 涛哥
 * @date 2021/11/22
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentVo {

    private String name;
    private String addr;
    private Integer cid;

}
