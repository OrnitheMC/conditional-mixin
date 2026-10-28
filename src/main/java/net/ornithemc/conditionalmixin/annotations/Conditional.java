package net.ornithemc.conditionalmixin.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation specifies that a Mixin class, field, or method should only
 * apply if certain conditions are met. This annotation can be use on classes,
 * fields, and methods. If the conditional on a Mixin class fails, the entire
 * Mixin will be discarded, regardless of the conditionals on any fields or
 * methods contained within.
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.METHOD })
public @interface Conditional {

	/**
	 * Specifies one or more {@linkplain Version} conditions that specify the
	 * required Minecraft versions for this condition to be met. All of the
	 * specified version conditions must be met. Leaving this field empty is
	 * equivalent to setting the value to {@code minecraftVersion = @Version("*")},
	 * i.e. allowing all Minecraft Versions.
	 * 
	 * @return the Minecraft {@linkplain Version} conditions for required Minecraft
	 *         .
	 */
	Version[] minecraftVersion() default {};

	/**
	 * Specifies the required game side for this condition to be met.
	 * 
	 * @return the required game side.
	 */
	GameSide gameSide() default GameSide.ANY;

	/**
	 * Specifies the required runtime environment for this condition to be met.
	 * 
	 * @return the required runtime environment.
	 */
	RuntimeEnvironment runtimeEnvironment() default RuntimeEnvironment.ANY;

	/**
	 * Specifies one or more {@linkplain Mod} conditions for mods that are required
	 * to be loaded for this condition to be met. All of the specified mod
	 * conditions must be met.
	 * 
	 * @return the {@linkplain Mod} conditions for required loaded mods.
	 */
	Mod[] modLoaded() default {};

	/**
	 * Specifies one or more {@linkplain Mod} conditions for mods that are required
	 * to be absent for this condition to be met. All of the specified mod
	 * conditions must be met.
	 * 
	 * @return the {@linkplain Mod} conditions for required absent mods.
	 */
	Mod[] modAbsent() default {};

}
