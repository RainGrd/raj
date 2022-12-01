package com.raj.dto;

import com.raj.entity.backend.Setmeal;
import com.raj.entity.backend.SetmealDish;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class SetmealDto extends Setmeal {

    private static final long serialVersionUID = -6809333568919310053L;

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
