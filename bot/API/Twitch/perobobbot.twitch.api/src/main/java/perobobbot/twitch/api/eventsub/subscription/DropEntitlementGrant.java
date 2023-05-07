package perobobbot.twitch.api.eventsub.subscription;

import lombok.RequiredArgsConstructor;
import perobobbot.twitch.api.Conditions;
import perobobbot.twitch.api.CriteriaType;
import perobobbot.twitch.api.SubscriptionType;
import perobobbot.twitch.api.eventsub.TwitchSubscriptionRequest;

@RequiredArgsConstructor
public class DropEntitlementGrant extends SubscriptionBase {

    public static final SubscriptionFactory FACTORY = conditions -> {
        final var helper = new ConditionHelper(conditions);
        return new DropEntitlementGrant(
                helper.get(CriteriaType.ORGANIZATION_ID),
                helper.find(CriteriaType.CATEGORY_ID).orElse(null),
                helper.find(CriteriaType.CAMPAIGN_ID).orElse(null)
        );
    };

    private final String organizationId;
    private final String categoryId;
    private final String campaignId;

    @Override
    public SubscriptionType getType() {
        return SubscriptionType.DROP_ENTITLEMENT_GRANT;
    }

    @Override
    public Conditions getCondition() {
        return Conditions.builder()
                         .put(CriteriaType.ORGANIZATION_ID, organizationId)
                         .put(CriteriaType.CATEGORY_ID, categoryId)
                         .put(CriteriaType.CAMPAIGN_ID, campaignId)
                         .build();
    }

    @Override
    public TwitchSubscriptionRequest.Builder completeRequest(TwitchSubscriptionRequest.Builder builder) {
        return super.completeRequest(builder).batchingEnabled(true);
    }
}
