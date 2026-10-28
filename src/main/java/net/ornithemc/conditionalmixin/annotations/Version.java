package net.ornithemc.conditionalmixin.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies a version condition used in a {@linkplain Conditional} or
 * {@linkplain Mod}. If used in a {@linkplain Conditional} the version
 * predicates can be specified as non-semver compliant version strings. If used
 * in a {@linkplain Mod} the version predicates must use semver-compliant
 * version strings.
 */
@Retention(RetentionPolicy.CLASS)
@Target({})
public @interface Version {

	/**
	 * Specifies one or more version predicates for a conditional or mod condition.
	 * If any one of the specified predicates matches the actually present version,
	 * this version condition is met.
	 * 
	 * @return the version predicates for this version condition.
	 */
	String[] value() default {};

	/**
	 * Specifies the game side in which this version condition must be met. If the
	 * game side does not match, this version condition will always evaluate to
	 * {@code true}.
	 * 
	 * @return the game side in which this version condition must be met.
	 */
	GameSide gameSide() default GameSide.ANY;

	/**
	 * Specifies the runtime environment in which this version condition must be
	 * met. If the runtime environment does not match, this version condition will
	 * always evaluate to {@code true}.
	 * 
	 * @return the runtime environment in which this version condition must be met.
	 */
	RuntimeEnvironment runtimeEnvironment() default RuntimeEnvironment.ANY;

}
