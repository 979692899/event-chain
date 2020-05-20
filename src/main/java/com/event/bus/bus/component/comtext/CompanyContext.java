package com.event.bus.bus.component.comtext;

import com.event.bus.bus.component.bean.CompanyBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyContext extends ExtraContext {

    private CompanyBean companyBean;

}
