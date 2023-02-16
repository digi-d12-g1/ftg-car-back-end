import com.ftgcar.dao.VehicleRepository;
import com.ftgcar.entity.Vehicle;
import com.ftgcar.exception.AlreadyExistsException;
import com.ftgcar.mapper.VehicleMapper;
import com.ftgcar.services.VehicleService;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

    @InjectMocks
    private VehicleService vehicleService;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private VehicleMapper vehicleMapper;

    @Nested
    class AddVehicleTest {

        @Test
        void addVehicleTest_withVehicleDto_savesVehicleAndReturnsVehicleDto() {
            // given
            VehicleDto vehicleDtoToAdd = new VehicleDto(1, "picture", "numberplate", "brand", "model", "available",
                    "category", 2);
            Vehicle vehicleToAdd = new Vehicle(1, "picture", "numberplate", "brand", "model", "available", "category",
                    2);
            when(vehicleRepository.findById(1)).thenReturn(Optional.isEmpty());
            when(vehicleRepository.findByNumberplate("numberplate")).thenReturn(Optional.isEmpty());
            when(vehicleMapper.vehicleDtoToVehicle(vehicleDtoToAdd)).thenReturn(vehicleToAdd);
            when(vehicleRepository.save(vehicle)).thenReturn(vehicle);
            when(vehicleMapper.vehicleToVehicleDto(vehicleToAdd)).thenReturn(vehicleDtoToAdd);

            // when
            VehicleDto returnedList = vehicleService.addVehicle(vehicleDtoToAdd);

            // then
            assertThat(returnedList).usingRecursiveComparison().isEqualTo(vehicleDtoToAdd);
        }

        @Test
        void addVehicleTest_withVehicleDtoNumberplateAlreadyExisting_throwsAlreadyExistsException() {
            // given
            VehicleDto vehicleDtoToAdd = new VehicleDto(1, "picture", "numberplate", "brand", "model", "available",
                    "category", 2);
            Vehicle existingVehicle = new Vehicle(1, "picture", "numberplate", "brand", "model", "available", "category",
                    2);
            when(vehicleRepository.findById(1)).thenReturn(Optional.isEmpty());
            when(vehicleRepository.findByNumberplate("numberplate")).thenReturn(Optional.of(existingVehicle));
           
            // when/then
            assertThatExceptionOfType(AlreadyExistsException.class).isThrownBy(
                () -> vehicleService.addVehicle(vehicleDtoToAdd)).withMessage("Le véhicule avec l'immatriculation numberplate existe déjà.");
        }
    }
}
