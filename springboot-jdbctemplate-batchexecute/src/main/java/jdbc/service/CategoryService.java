package jdbc.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jdbc.entity.Category;
import jdbc.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author lequal
 * @since 2022-11-22
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryService extends ServiceImpl<CategoryMapper, Category> {

    private final CategoryMapper categoryMapper;

    public void clearTable() {
        categoryMapper.clearTable();
    }
}

