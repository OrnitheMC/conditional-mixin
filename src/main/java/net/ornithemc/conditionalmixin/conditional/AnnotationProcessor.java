package net.ornithemc.conditionalmixin.conditional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import net.ornithemc.conditionalmixin.ConditionalMixin;

public final class AnnotationProcessor {

	private final Map<String, Boolean> checkedTargets = new HashMap<>();

	public boolean shouldRemove(ClassNode classNode) {
		return this.shouldRemove(classNode.name, classNode.invisibleAnnotations);
	}

	public boolean shouldRemove(ClassNode classNode, FieldNode fieldNode) {
		return this.shouldRemove(classNode.name + "." + fieldNode.name + fieldNode.desc, fieldNode.invisibleAnnotations);
	}

	public boolean shouldRemove(ClassNode classNode, MethodNode methodNode) {
		return this.shouldRemove(classNode.name + "." + methodNode.name + methodNode.desc, methodNode.invisibleAnnotations);
	}

	private boolean shouldRemove(String target, List<AnnotationNode> annotations) {
		return this.checkedTargets.computeIfAbsent(target, k -> checkShouldRemove(target, annotations));
	}

	private static boolean checkShouldRemove(String target, List<AnnotationNode> annotations) {
		ConditionalMixin.LOGGER.debug("Checking Conditional Mixin annotations for {}", target);

		if (annotations != null) {
			for (AnnotationNode node : annotations) {
				if (AnnotationProcessor.isFailingCondition(node)) {
					ConditionalMixin.LOGGER.debug("Preventing application of mixin {}.{} due to conditions not being met", target);
					return true;
				}
			}
		}

		return false;
	}

	public static boolean isCondition(AnnotationNode node) {
		return Condition.DESCRIPTOR.equals(node.desc);
	}

	public static boolean isFailingCondition(AnnotationNode node) {
		if (Condition.DESCRIPTOR.equals(node.desc)) {
			Condition condition = Condition.parse(node);

			if (!condition.test()) {
				return true;
			}
		}

		return false;
	}
}
