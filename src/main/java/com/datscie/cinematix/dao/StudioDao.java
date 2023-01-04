package com.datscie.cinematix.dao;

import java.util.ArrayList;

import com.datscie.cinematix.models.Studio;
import com.datscie.cinematix.utils.ApplicationException;

public interface StudioDao {
    public ArrayList<Studio> getAllStudios() throws ApplicationException;
    public void addStudio(Studio studio) throws ApplicationException;
    public void updateStudio(Studio studio) throws ApplicationException;
    public void deleteStudio(Studio studio) throws ApplicationException;
}
