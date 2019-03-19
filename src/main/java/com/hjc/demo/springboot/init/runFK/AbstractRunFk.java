package com.hjc.demo.springboot.init.runFK;

public abstract class AbstractRunFk {

    private AbstractRunFk next;

    public AbstractRunFk setNext(AbstractRunFk next) {
        this.next = next;
        return next;
    }

    public final void support(RunFKEntity trouble) {
        if(isRunFK(trouble)) {
            runFK(trouble);
        } else if (next != null) {
            next.support(trouble);
        } else {
            notRunFK(trouble);
        }
    }

    protected abstract boolean isRunFK(RunFKEntity trouble);

    protected void runFK(Object trouble) {
        System.out.println(trouble + " is resolved by " + this + ".");
    }

    protected void notRunFK(Object trouble) {  // 未解决
        System.out.println(trouble + " cannot be resolved.");
    }
}
