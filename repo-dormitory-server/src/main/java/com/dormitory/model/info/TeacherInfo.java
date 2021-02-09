package com.dormitory.model.info;

import com.dormitory.model.AbstractModel;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
@Table(name = "teach_info")
@Proxy(lazy = false)
public class TeacherInfo extends AbstractModel {

    private String boardContent;

}

