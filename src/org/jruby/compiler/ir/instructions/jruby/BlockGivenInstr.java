package org.jruby.compiler.ir.instructions.jruby;

import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.instructions.Instr;
import org.jruby.compiler.ir.instructions.ResultInstr;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.operands.Variable;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;

public class BlockGivenInstr extends Instr implements ResultInstr {
    private final Variable result;
    
    public BlockGivenInstr(Variable result) {
        super(Operation.BLOCK_GIVEN);
        
        assert result != null: "BlockGivenInstr result is null";
        
        this.result = result;
    }

    public Operand[] getOperands() {
        return EMPTY_OPERANDS;
    }
    
    public Variable getResult() {
        return result;
    }

    @Override
    public Instr cloneForInlining(InlinerInfo ii) {
        return new BlockGivenInstr(ii.getRenamedVariable(result));
    }

    @Override
    public Object interpret(InterpreterContext interp, ThreadContext context, IRubyObject self, Block block, Object exception, Object[] temp) {
        result.store(interp, context, self, context.getRuntime().newBoolean(block.isGiven()), temp);
        return null;
    }
}
