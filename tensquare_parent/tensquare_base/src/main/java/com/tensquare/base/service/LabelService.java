package com.tensquare.base.service;

import com.google.common.collect.Lists;
import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
@Transactional
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll() {
        return labelDao.findAll();
    }

    public Label findById(String id) {
        return labelDao.findById(id).get();
    }

    public void save(Label label) {
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }

    public void update(Label label) {
        labelDao.save(label);
    }

    public void deleteById(String id) {
        labelDao.deleteById(id);
    }

    public List<Label> findSearch(Label label) {

        return labelDao.findAll(new Specification<Label>() {
            /**
             * @param root 根对象,也就是要把条件封装到哪个对象中. where 类名 = label.getid
             * @param query 封装的都是查询关键字, 比如 group by order by等
             * @param cb 用来封装条件对象的, 如果直接返回null, 表是不需要任何条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //new 一个list集合, 存放所有条件
                List<Predicate> list = Lists.newArrayList();
                if (label.getLabelname() != null && !"".equals(label.getLabelname())) {
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");//where labelname like "%小明%"
                    list.add(predicate);
                }
                if (label.getState() != null && !"".equals(label.getState())) {
                    Predicate predicate = cb.equal(root.get("state").as(String.class), label.getState());//status = "1"
                    list.add(predicate);
                }
                // new 一个数组作为最终返回值的条件
                Predicate[] parr = new Predicate[list.size()];
                // 把list转换为数组
                list.toArray(parr);
                return cb.and(parr);  // where labelname like "%小明%" and  state = "1"
            }
        });
    }

    public Page<Label> pageQuery(Label label, int page, int size) {
        //封装分页对象
        Pageable pageable = PageRequest.of(page - 1, size);
        return labelDao.findAll(new Specification<Label>() {
            /**
             * @param root 根对象,也就是要把条件封装到哪个对象中. where 类名 = label.getid
             * @param query 封装的都是查询关键字, 比如 group by order by等
             * @param cb 用来封装条件对象的, 如果直接返回null, 表是不需要任何条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //new 一个list集合, 存放所有条件
                List<Predicate> list = Lists.newArrayList();
                if (label.getLabelname() != null && !"".equals(label.getLabelname())) {
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");//where labelname like "%小明%"
                    list.add(predicate);
                }
                if (label.getState() != null && !"".equals(label.getState())) {
                    Predicate predicate = cb.equal(root.get("state").as(String.class), label.getState());//status = "1"
                    list.add(predicate);
                }
                // new 一个数组作为最终返回值的条件
                Predicate[] parr = new Predicate[list.size()];
                // 把list转换为数组
                list.toArray(parr);
                return cb.and(parr);  // where labelname like "%小明%" and  state = "1"
            }
        }, pageable);
    }
}
