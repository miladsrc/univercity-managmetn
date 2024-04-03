package base.entity;

import lombok.*;

import java.io.Serializable;


@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BaseEntity <ID extends Serializable> {
    private ID id;
}