package com.samiei.central.exceptionHandling;

public class Crash {





 private Builder builder;

 private String crashClass;

 private String crashMethod;

 private String crashFile;

 private String crashLine;

 private String crashStackTrace;

 private String errorMessage;


    public String getCrashClass() {
        return crashClass;
    }

    public String getCrashMethod() {
        return crashMethod;
    }

    public String getCrashFile() {
        return crashFile;
    }

    public String getCrashLine() {
        return crashLine;
    }

    public String getCrashStackTrace() {
        return crashStackTrace;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Crash(String errorMessage,String crashClass,String crashMethod,String crashLine,String crashFile,String crashStackTrace,Builder builder) {
        this.builder = builder;
        this.crashClass = crashClass;
        this.crashMethod = crashMethod;
        this.crashFile = crashFile;
        this.crashLine = crashLine;
        this.crashStackTrace = crashStackTrace;
        this.errorMessage = errorMessage;
    }

    public static class Builder{

        private String crashClass;
        private String crashMethod;
        private String crashFile;
        private String crashLine;
        private String crashStackTrace;
        private String errorMessage;

        public Builder setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }


        public Builder setCrashClass(String crashClass) {
            this.crashClass = crashClass;
            return this;
        }



        public Builder setCrashMethod(String crashMethod) {
            this.crashMethod = crashMethod;
            return this;
        }


        public Builder setCrashFile(String crashFile) {
            this.crashFile = crashFile;
            return this;
        }



        public Builder setCrashLine(String crashLine) {
            this.crashLine = crashLine;
            return this;
        }

        public Builder setStackTrace(String crashStackTrace) {
            this.crashStackTrace = crashStackTrace;
            return this;
        }

        public Crash build() {
            return new Crash(errorMessage,crashClass,crashMethod,crashLine,crashFile,crashStackTrace,this);
        }


    }

    @Override
    public String toString() {
        return "Crash{" +
                "builder=" + builder +
                ", crashClass='" + crashClass + '\'' +
                ", crashMethod='" + crashMethod + '\'' +
                ", crashFile='" + crashFile + '\'' +
                ", crashLine='" + crashLine + '\'' +
                ", crashStackTrace='" + crashStackTrace + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
