package com.duong.ss17.repository.hw04_hw05;

import com.duong.ss17.dto.hw04_hw05.AddCartDTO;
import com.duong.ss17.dto.hw04_hw05.UpdateCartDTO;
import com.duong.ss17.entity.hw03.Product;
import com.duong.ss17.entity.hw04_hw05.Cart;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class CartRepoImpl implements CartRepo {
    private final SessionFactory sessionFactory;

    public CartRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public boolean insert(AddCartDTO dto) {
        try {
            Cart cart = new Cart();
            cart.setCustomerId(dto.getCustomerId());
            cart.setProductId(dto.getProductId());
            cart.setQuantity(dto.getQuantity());

            getSession().persist(cart);
            return true;
        } catch (Exception e) {
            System.err.println("Insert error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateQuantity(UpdateCartDTO dto) {
        try {
            int affected = getSession().createMutationQuery(
                            "update Cart set quantity = :qty where id = :id")
                    .setParameter("qty", dto.getQuantity())
                    .setParameter("id", dto.getId())
                    .executeUpdate();

            return affected > 0;
        } catch (Exception e) {
            System.err.println("Update error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            int affected = getSession().createMutationQuery(
                            "delete from Cart where id = :id")
                    .setParameter("id", id)
                    .executeUpdate();

            return affected > 0;
        } catch (Exception e) {
            System.err.println("Delete error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Cart> findByCustomerId(int customerId) {
        return getSession().createQuery(
                        "from Cart where customerId = :cid", Cart.class
                )
                .setParameter("cid", customerId)
                .list();
    }

    @Override
    public double calculateTotalPrice(int customerId) {
        String hql = """
            select sum(p.price * c.quantity)
            from Cart c
            join Product p on c.productId = p.id
            where c.customerId = :cid
        """;

        Double result = getSession().createQuery(hql, Double.class)
                .setParameter("cid", customerId)
                .uniqueResult();

        return result != null ? result : 0.0;
    }


    @Override
    public List<Product> getProductsInCart(int customerId) {
        return getSession().createQuery(
                        "select p from Product p join Cart c on p.id = c.productId where c.customerId = :id", Product.class)
                .setParameter("id", customerId)
                .list();
    }

    @Override
    public double getTotalMoney(int customerId) {
        Double total = getSession().createQuery(
                        "select sum(p.price * c.quantity) from Product p join Cart c on p.id = c.productId where c.customerId = :id", Double.class)
                .setParameter("id", customerId)
                .uniqueResult();
        return total != null ? total : 0.0;
    }

    @Override
    public void clearCart(int customerId) {
        getSession().createQuery("delete from Cart where customerId = :id")
                .setParameter("id", customerId)
                .executeUpdate();
    }
}
