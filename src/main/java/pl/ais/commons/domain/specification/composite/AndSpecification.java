package pl.ais.commons.domain.specification.composite;

import java.lang.reflect.Array;
import java.util.Arrays;

import javax.annotation.Nonnull;

import pl.ais.commons.domain.specification.Specification;

/**
 * Composite specification being conjunction of other specifications.
 *
 * @param <C> determines the type of candidate
 * @author Warlock, AIS.PL
 * @since 1.0.1
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public final class AndSpecification<C> extends AbstractCompositeSpecification<C> {

    private final Specification<C>[] specifications;

    /**
     * Constructs new instance.
     *
     * @param first first specification for which we create conjunction
     * @param others other specifications for which we create conjunction
     */
    @SafeVarargs
    public AndSpecification(@Nonnull final Specification<C> first, final Specification<C>... others) {
        super();
        this.specifications = (Specification<C>[]) Array.newInstance(first.getClass(), others.length + 1);
        this.specifications[0] = first;
        System.arraycopy(others, 0, this.specifications, 1, others.length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Specification<C>[] getComponents() {
        return Arrays.copyOf(specifications, specifications.length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends C> boolean isSatisfiedBy(final T candidate) {
        boolean result = true;
        for (final Specification<C> specification : specifications) {
            if (!specification.isSatisfiedBy(candidate)) {
                result = false;
                break;
            }
        }
        return result;
    }

}
