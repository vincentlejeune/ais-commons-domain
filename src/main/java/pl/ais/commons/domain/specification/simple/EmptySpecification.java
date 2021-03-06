package pl.ais.commons.domain.specification.simple;

import java.util.Collection;
import java.util.Map;

import javax.annotation.concurrent.Immutable;

import pl.ais.commons.domain.specification.Specification;

/**
 * Specification implementation which is satisfied only if provided candidate is empty.
 *
 * <p>
 *     Note, that this specification can be applied to either Collection, Map or String values;
 *     candidate having neither of the above types (including {@code null}) will never satisfy this specification.
 * </p>
 *
 * @param <C> determines the type of candidate
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
@Immutable
public final class EmptySpecification<C> implements Specification<C> {

    /**
     * Defines singleton instance of {@link EmptySpecification}.
     */
    @SuppressWarnings("rawtypes")
    public static final Specification INSTANCE = new EmptySpecification();

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends C> boolean isSatisfiedBy(final T candidate) {
        boolean result = false;
        processing: {
            if (candidate instanceof Collection) {
                result = ((Collection<?>) candidate).isEmpty();
                break processing;
            }
            if (candidate instanceof Map) {
                result = ((Map<?, ?>) candidate).isEmpty();
                break processing;
            }
            if (candidate instanceof String) {
                result = ((String) candidate).isEmpty();
                break processing;
            }
        }
        return result;
    }

}
