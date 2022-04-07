package com.drivertest.donatenowapp.remote

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type


class NullOnEmptyConvertorFactory:Converter.Factory() {


//    @Override
//    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
//        final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);
//        return new Converter<ResponseBody, Object>() {
//            @Override
//            public Object convert(ResponseBody body) throws IOException {
//                if (body.contentLength() == 0) return null;
//                return delegate.convert(body);
//            }
//        };
override fun responseBodyConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Converter<ResponseBody, *> {
    val delegate: Converter<ResponseBody, Any> = retrofit.nextResponseBodyConverter(this, type, annotations)
    return Converter { body -> if (body.contentLength() == 0L) null else delegate.convert(body) }
}
}