package org.jruby.compiler.ir.instructions.calladapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jruby.RubyArray;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.operands.Splat;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Block;
import org.jruby.runtime.CallSite;
import org.jruby.runtime.CallType;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;

/**
 * AttrAssign is weird.  self[i] = 1 is treated as a functional call and 
 * it also returns no result. 
 */
public class AttrAssignCallAdapter extends CallAdapter {
    private String name;
    private Operand[] args;
    
    public AttrAssignCallAdapter(CallSite callSite, String name, Operand[] args) {
        super(callSite);
        
        this.args = args;
        this.name = name;
    }

    @Override
    public Object call(InterpreterContext interp, ThreadContext context, IRubyObject self, IRubyObject receiver, Object[] temp) {
        IRubyObject[] values = prepareArguments(interp, context, self, args, temp);
        
        if (callSite == null) {
            CallType callType = self == receiver ? CallType.FUNCTIONAL : CallType.NORMAL;
            RuntimeHelpers.invoke(context, receiver, name, values, callType, Block.NULL_BLOCK);
        } else {
            callSite.call(context, self, receiver, values);
        }
        
        return null;
    }
    
    protected IRubyObject[] prepareArguments(InterpreterContext interp, ThreadContext context, IRubyObject self, Operand[] args, Object[] temp) {
        List<IRubyObject> argList = new ArrayList<IRubyObject>();
        for (int i = 0; i < args.length; i++) {
            IRubyObject rArg = (IRubyObject) args[i].retrieve(interp, context, self, temp);
            if (args[i] instanceof Splat) {
                argList.addAll(Arrays.asList(((RubyArray) rArg).toJavaArray()));
            } else {
                argList.add(rArg);
            }
        }

        return argList.toArray(new IRubyObject[argList.size()]);
    } 
}
