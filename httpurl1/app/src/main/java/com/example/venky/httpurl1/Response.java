package com.example.venky.httpurl1;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by venky on 15/5/16.
 */
public class Response implements List<Response> {


    /**
     * billedret : 0
     * class : com.enteleki.sfa.botree.RetailerSummary
     * ctgcode : G003
     * ctgname : Cosmetic
     * distcode : DL/0004
     * mktid : 6
     * rtradd1 : D 12/225 Sec 7
     * rtrclassid : 12
     * rtrcode : RE0012
     * rtrid : 5
     * rtrname : Aggarwal Cosmetics
     * rtrphoneno : 27049003
     * srpcde : 2
     * valueclasscode : CL004
     * valueclassname : D
     */

    private int billedret;
    @SerializedName("class")
    private String classX;
    private String ctgcode;
    private String ctgname;
    private String distcode;
    private int mktid;
    private String rtradd1;
    private int rtrclassid;
    private String rtrcode;
    private int rtrid;
    private String rtrname;
    private String rtrphoneno;
    private String srpcde;
    private String valueclasscode;
    private String valueclassname;

    public int getBilledret() {
        return billedret;
    }

    public void setBilledret(int billedret) {
        this.billedret = billedret;
    }

    public String getClassX() {
        return classX;
    }

    public void setClassX(String classX) {
        this.classX = classX;
    }

    public String getCtgcode() {
        return ctgcode;
    }

    public void setCtgcode(String ctgcode) {
        this.ctgcode = ctgcode;
    }

    public String getCtgname() {
        return ctgname;
    }

    public void setCtgname(String ctgname) {
        this.ctgname = ctgname;
    }

    public String getDistcode() {
        return distcode;
    }

    public void setDistcode(String distcode) {
        this.distcode = distcode;
    }

    public int getMktid() {
        return mktid;
    }

    public void setMktid(int mktid) {
        this.mktid = mktid;
    }

    public String getRtradd1() {
        return rtradd1;
    }

    public void setRtradd1(String rtradd1) {
        this.rtradd1 = rtradd1;
    }

    public int getRtrclassid() {
        return rtrclassid;
    }

    public void setRtrclassid(int rtrclassid) {
        this.rtrclassid = rtrclassid;
    }

    public String getRtrcode() {
        return rtrcode;
    }

    public void setRtrcode(String rtrcode) {
        this.rtrcode = rtrcode;
    }

    public int getRtrid() {
        return rtrid;
    }

    public void setRtrid(int rtrid) {
        this.rtrid = rtrid;
    }

    public String getRtrname() {
        return rtrname;
    }

    public void setRtrname(String rtrname) {
        this.rtrname = rtrname;
    }

    public String getRtrphoneno() {
        return rtrphoneno;
    }

    public void setRtrphoneno(String rtrphoneno) {
        this.rtrphoneno = rtrphoneno;
    }

    public String getSrpcde() {
        return srpcde;
    }

    public void setSrpcde(String srpcde) {
        this.srpcde = srpcde;
    }

    public String getValueclasscode() {
        return valueclasscode;
    }

    public void setValueclasscode(String valueclasscode) {
        this.valueclasscode = valueclasscode;
    }

    public String getValueclassname() {
        return valueclassname;
    }

    public void setValueclassname(String valueclassname) {
        this.valueclassname = valueclassname;
    }

    @Override
    public void add(int location, Response object) {

    }

    @Override
    public boolean add(Response object) {
        return false;
    }

    @Override
    public boolean addAll(int location, Collection<? extends Response> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Response> collection) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean contains(Object object) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public Response get(int location) {
        return null;
    }

    @Override
    public int indexOf(Object object) {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @NonNull
    @Override
    public Iterator<Response> iterator() {
        return null;
    }

    @Override
    public int lastIndexOf(Object object) {
        return 0;
    }

    @Override
    public ListIterator<Response> listIterator() {
        return null;
    }

    @NonNull
    @Override
    public ListIterator<Response> listIterator(int location) {
        return null;
    }

    @Override
    public Response remove(int location) {
        return null;
    }

    @Override
    public boolean remove(Object object) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public Response set(int location, Response object) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @NonNull
    @Override
    public List<Response> subList(int start, int end) {
        return null;
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @NonNull
    @Override
    public <T> T[] toArray(T[] array) {
        return null;
    }
}
