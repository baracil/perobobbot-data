package perobobbot.domain.jpa.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import perobobbot.api.data.JoinedChannel;

import javax.persistence.*;

@Entity
@Table(name = "JOINED_CHANNEL",uniqueConstraints = {@UniqueConstraint(columnNames = {"NAME","USER_IDENTITY_ID"},name = "uk_name__user_identity_id")})
@NoArgsConstructor
@Getter
@Setter(AccessLevel.PROTECTED)
public class JoinedChannelEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "USER_IDENTITY_ID",
            foreignKey = @ForeignKey(name = "FK_JOINED_CHANNEL__USER_IDENTITY"),
            nullable = false)
    private UserIdentityEntity userIdentity;

    @Column(name = "USER_IDENTITY_ID",nullable = false, insertable = false, updatable = false)
    private long userIdentityId;

    @Column(name = "NAME",nullable = false)
    private String name;

    @Column(name = "READ_ONLY",nullable = false)
    @Setter
    private boolean readOnly;

    JoinedChannelEntity(UserIdentityEntity userIdentity, String name, boolean readOnly) {
        this.userIdentity = userIdentity;
        this.userIdentityId = userIdentity.getId();
        this.name = name;
        this.readOnly = readOnly;
    }

    public JoinedChannel toView() {
        return new JoinedChannel(getId(), userIdentityId, name, readOnly);
    }
}
