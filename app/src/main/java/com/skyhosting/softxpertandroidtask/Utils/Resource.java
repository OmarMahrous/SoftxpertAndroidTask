package com.skyhosting.softxpertandroidtask.Utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Resource {

    @NotNull
    private static Status status;
    @Nullable
    private Object data = null;
    @Nullable
    private String message = "";

    @Nullable
    private Throwable throwable;

    public Resource(@NotNull Status status, @Nullable Throwable throwable) {
        this.throwable = throwable;
    }

    @NotNull
    public final Status getStatus() {
        return status;
    }

    @Nullable
    public final Object getData() {
        return this.data;
    }

    @Nullable
    public final String getMessage() {
        return this.message;
    }

    @Nullable
    public Throwable getThrowable() {
        return throwable;
    }

    public Resource(@NotNull Status status, @Nullable Object data, @Nullable String message) {
//        Intrinsics.checkParameterIsNotNull(status, "status");
        this.status = status;
        this.data = data;
        this.message = message;
    }

    @NotNull
    public final Status component1() {
        return this.status;
    }

    @Nullable
    public final Object component2() {
        return this.data;
    }

    @Nullable
    public final String component3() {
        return this.message;
    }

    @NotNull
    public final Resource copy(@NotNull Status status, @Nullable Object data, @Nullable String message) {
//        Intrinsics.checkParameterIsNotNull(status, "status");
        return new Resource(status, data, message);
    }

    // $FF: synthetic method
    public static Resource copy$default(Resource var0, Status var1, Object var2, String var3, int var4, Object var5) {
        if ((var4 & 1) != 0) {
            var1 = var0.status;
        }

        if ((var4 & 2) != 0) {
            var2 = var0.data;
        }

        if ((var4 & 4) != 0) {
            var3 = var0.message;
        }

        return var0.copy(var1, var2, var3);
    }

    @NotNull
    public String toString() {
        return "Resource(status=" + this.status + ", data=" + this.data + ", message=" + this.message + ")";
    }

    public int hashCode() {
        Status var10000 = this.status;
        int var1 = (var10000 != null ? var10000.hashCode() : 0) * 31;
        Object var10001 = this.data;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        String var2 = this.message;
        return var1 + (var2 != null ? var2.hashCode() : 0);
    }

    public boolean equals(@Nullable Object var1) {
        if (this != var1) {
            if (var1 instanceof Resource) {
                Resource var2 = (Resource) var1;
//                if (Intrinsics.areEqual(this.status, var2.status) && Intrinsics.areEqual(this.data, var2.data) && Intrinsics.areEqual(this.message, var2.message)) {
//                    return true;
//                }
            }

            return false;
        } else {
            return true;
        }
    }

    @NotNull
    public static final Resource success(@Nullable Object data, String msg) {
        return new Resource(Status.SUCCESS, data, msg);
    }

    @NotNull
    public static final Resource error(@NotNull String msg, @Nullable Object data) {
//        Intrinsics.checkParameterIsNotNull(msg, "msg");
        return new Resource(Status.ERROR, data, msg);
    }

    @NotNull
    public static final Resource error2(@NotNull Throwable throwable) {
//        Intrinsics.checkParameterIsNotNull(msg, "msg");
        return new Resource(Status.ERROR, throwable);
    }

    @NotNull
    public static final Resource loading(@Nullable Object data) {
        return new Resource(Status.LOADING, data, (String) null);
    }

}
