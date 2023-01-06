package nya.nekoneko.vultr;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nya.nekoneko.vultr.model.page.VultrPageMeta;

import java.util.List;

/**
 * 包装列表数据
 *
 * @author Ho
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VultrResult<T> {
    private List<T> list;
    private VultrPageMeta meta;
}
