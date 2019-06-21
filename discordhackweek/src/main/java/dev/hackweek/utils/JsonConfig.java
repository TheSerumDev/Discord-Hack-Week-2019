package dev.hackweek.utils;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import lombok.Getter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class JsonConfig{
    private final String filename;
    private final File source;
    @Getter
    private JsonObject jsonObject;
    @Getter
    private final JsonObject defaultObject;

    public JsonConfig( final String filename, final File datafolder ){
        this.filename = filename;
        this.source = new File( datafolder, filename + ".json" );
        this.defaultObject = new JsonObject();
    }

    public JsonConfig( final File file){
        this.source = file;
        this.filename = file.getName();
        this.defaultObject = new JsonObject();
    }

    public JsonConfig addDefault( final String key, final Object value ){
        this.defaultObject.add( key, new JsonPrimitive( value.toString() ) );
        return this;
    }

    public JsonConfig load(){
        if ( !this.source.exists() ){
            try{
                this.source.createNewFile();
                this.write( this.source, this.convertToString( this.defaultObject ) );
            } catch (final IOException e){
                e.printStackTrace();
            }
        }
        this.read();
        return this;
    }

    public JsonConfig save() throws IOException{
        this.write( this.source, this.convertToString( this.jsonObject ) );
        return this;
    }

    private void read(){
        try{
            this.jsonObject = new JsonParser().parse( new String( Files.readAllBytes( this.source.toPath() ) ) ).getAsJsonObject();
        } catch (final IOException e){
            e.printStackTrace();
        }
    }

    private void write( final File file, final String string ) throws IOException{
        final FileWriter fileWriter = new FileWriter( file );
        fileWriter.write( string );
        fileWriter.flush();
        fileWriter.close();
    }

    public void setString( final String key, final String value ){
        this.jsonObject.addProperty( key, value );
    }

    public void setBoolean( final String key, final Boolean value ){
        this.jsonObject.addProperty( key, value );
    }

    public void setInteger( final String key, final Integer value ){
        this.jsonObject.addProperty( key, value );
    }

    public void setLong( final String key, final Long value ){
        this.jsonObject.addProperty( key, value );
    }

    public void setFloat( final String key, final Float value ){
        this.jsonObject.addProperty( key, value );
    }

    public void setDouble( final String key, final Double value ){
        this.jsonObject.addProperty( key, value );
    }

    public void setShort( final String key, final Short value ){
        this.jsonObject.addProperty( key, value );
    }

    public void setByte( final String key, final Byte value ){
        this.jsonObject.addProperty( key, value );
    }


    public Integer getInteger( final String key ){
        return this.jsonObject.get( key ).getAsInt();
    }

    public Boolean getBoolean( final String key ){
        return this.jsonObject.get( key ).getAsBoolean();
    }

    public String getString( final String key ){
        return this.jsonObject.get( key ).getAsString();
    }

    public Float getFloat( final String key ){
        return this.jsonObject.get( key ).getAsFloat();
    }

    public Double getDouble( final String key ){
        return this.jsonObject.get( key ).getAsDouble();
    }

    public Long getLong( final String key ){
        return this.jsonObject.get( key ).getAsLong();
    }

    public Short getShort( final String key ){
        return this.jsonObject.get( key ).getAsShort();
    }

    public Byte getByte( final String key ){
        return this.jsonObject.get( key ).getAsByte();
    }

    public void setObject( final String key, final Object value ){
        this.jsonObject.add( key, new JsonPrimitive( value.toString() ) );
    }

    public String convertToString( final JsonObject jsonObject ){
        return new GsonBuilder().setPrettyPrinting().create().toJson( jsonObject );
    }

    public void setJsonObject(final  JsonObject jsonObject){
        this.jsonObject = jsonObject;
    }

}
