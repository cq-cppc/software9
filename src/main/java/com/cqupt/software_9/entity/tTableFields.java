package com.cqupt.software_9.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class tTableFields implements Serializable {
   private List<tTableField> field;
   private String disease;
}
