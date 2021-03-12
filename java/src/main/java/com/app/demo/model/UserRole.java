package com.app.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UserRole {

    @EmbeddedId
    private UserRoleId id;

    @ManyToOne
    @JoinColumn(name = "fk_user", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_role", insertable = false, updatable = false)
    private Role role;

    public UserRole(User b, Role p) {
        // create primary key
        this.id = new UserRoleId(b.getId(), p.getId());

        // initialize attributes
        this.user = b;
        this.role = p;

        // update relationships to assure referential integrity
        p.getUser().add(this);
        b.getRole().add(this);
    }

    public UserRole() {

    }

    @Embeddable
    public static class UserRoleId implements Serializable {

        @Column(name = "fk_user")
        protected Integer userId;

        @Column(name = "fk_role")
        protected Integer roleId;

        public UserRoleId() {

        }

        public UserRoleId(Integer userId, Integer roleId) {
            this.userId = userId;
            this.roleId = roleId;
        }


    }


}
