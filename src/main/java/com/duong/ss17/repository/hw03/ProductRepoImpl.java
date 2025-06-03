package com.duong.ss17.repository.hw03;

import com.duong.ss17.entity.hw03.Product;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ProductRepoImpl implements ProductRepo{
    private final SessionFactory sessionFactory;

    public ProductRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession (){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Product> getProductsPerPage(int pageNumber, int pageSize) {
        int firstResult = (pageNumber - 1) * pageSize;

        return getSession().createQuery("from Product ", Product.class)
                .setFirstResult(firstResult)
                .setMaxResults(pageSize)
                .list();
    }

    @Override
    public int countProducts() {
        Long count = getSession()
                .createQuery("select count(p.id) from Product p",Long.class)
                .uniqueResult();
        return count != null ? count.intValue() : 0;
    }

    @Override
    public Optional<Product> findById(int id) {
        return getSession().createQuery("from Product where id = :p_id",Product.class)
                .setParameter("p_id",id)
                .uniqueResultOptional();
    }
}
