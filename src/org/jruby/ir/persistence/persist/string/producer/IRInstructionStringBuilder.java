package org.jruby.ir.persistence.persist.string.producer;

import org.jruby.ir.instructions.Instr;
import org.jruby.ir.instructions.ResultInstr;
import org.jruby.ir.operands.Variable;

public class IRInstructionStringBuilder extends AbstractIRStringBuilder<Instr> {
    private static final String PARAMETER_LIST_START_MARKER = "(";
    private static final String PARAMETER_SEPARATOR = ", ";
    private static final String PARAMETER_LIST_END_MARKER = ")";

    private static final String EQUAL = " = ";

    private static final String DEAD_MARKER = "[DEAD]";
    private static final String HAS_UNUSED_RESULT_MARKER = "[DEAD-RESULT]";

    public IRInstructionStringBuilder(AbstractIRStringBuilder parentBuilder) {
        super(parentBuilder);
    }

    @Override
    String getParameterListStartMarker() {
        return PARAMETER_LIST_START_MARKER;
    }

    @Override
    String getParameterSeparator() {
        return PARAMETER_SEPARATOR;
    }

    @Override
    String getParameterListEndMarker() {
        return PARAMETER_LIST_END_MARKER;
    }

    public void appendPrefix(final Instr instr) {
        if (instr instanceof ResultInstr) {
            final Variable result = ((ResultInstr) instr).getResult();
            appendVerbatim(result);
            appendVerbatim(EQUAL);
        }
        appendVerbatim(instr.getOperation().name());
    }

    public void appendMarkers(final Instr instr) {
        if (instr.hasUnusedResult()) {
            appendVerbatim(HAS_UNUSED_RESULT_MARKER);
        }
        if (instr.isDead()) {
            appendVerbatim(DEAD_MARKER);
        }
    }
}
