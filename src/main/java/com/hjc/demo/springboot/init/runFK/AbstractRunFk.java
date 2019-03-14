package com.hjc.demo.springboot.init.runFK;

public abstract class AbstractRunFk {

    private AbstractRunFk next;

    public AbstractRunFk setNext(AbstractRunFk next) {
        this.next = next;
        return next;
    }

    public final void support(Object trouble) {
        if(resolve(trouble)) {
            done(trouble);
        } else if (next != null) {
            next.support(trouble);
        } else {
            fail(trouble);
        }
    }

    protected abstract boolean resolve(Object trouble);

    protected void done(Object trouble) {
        System.out.println(trouble + " is resolved by " + this + ".");
    }

    protected void fail(Object trouble) {  // 未解决
        System.out.println(trouble + " cannot be resolved.");
    }
}
