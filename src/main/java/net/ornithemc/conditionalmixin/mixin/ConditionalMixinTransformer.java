package net.ornithemc.conditionalmixin.mixin;

import java.util.List;

import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import net.ornithemc.conditionalmixin.ConditionalMixin;
import net.ornithemc.conditionalmixin.conditional.AnnotationProcessor;

public final class ConditionalMixinTransformer {

	private final AnnotationProcessor annotationProcessor;

	public ConditionalMixinTransformer(AnnotationProcessor annotationProcessor) {
		this.annotationProcessor = annotationProcessor;
	}

	public void transform(ClassNode classNode) {
		ConditionalMixin.LOGGER.debug("Transforming mixin {}", classNode.name);

		removeConditionAnnotations(classNode.invisibleAnnotations);

		classNode.fields.removeIf(field -> this.annotationProcessor.shouldRemove(classNode, field));
		classNode.methods.removeIf(method -> this.annotationProcessor.shouldRemove(classNode, method));

		classNode.fields.forEach(this::transform);
		classNode.methods.forEach(this::transform);
	}

	public void transform(FieldNode fieldNode) {
		removeConditionAnnotations(fieldNode.invisibleAnnotations);
	}

	public void transform(MethodNode methodNode) {
		removeConditionAnnotations(methodNode.invisibleAnnotations);
	}

	private static void removeConditionAnnotations(List<AnnotationNode> annotations) {
		if (annotations != null) {
			annotations.removeIf(AnnotationProcessor::isCondition);
		}
	}
}
