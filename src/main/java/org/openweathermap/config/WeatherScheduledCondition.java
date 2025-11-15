package org.openweathermap.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Custom Spring {@link Condition} that checks whether the weather scheduler
 * should be enabled based on the "weather.scheduled" property.
 * <p>
 * The condition evaluates to {@code true} if the property is set to {@code true},
 * otherwise it evaluates to {@code false} and any bean annotated with
 * {@code @Conditional(WeatherScheduledCondition.class)} will not be created.
 * </p>
 */
public class WeatherScheduledCondition implements Condition {

    /**
     * Determines whether the condition matches.
     *
     * @param context  the {@link ConditionContext} providing access to the environment and bean definitions
     * @param metadata metadata of the {@link org.springframework.context.annotation.Conditional} annotation
     * @return {@code true} if the "weather.scheduled" property is set to true, {@code false} otherwise
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String scheduled = context.getEnvironment().getProperty("weather.scheduled");
        return Boolean.parseBoolean(scheduled);
    }
}