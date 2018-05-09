-- phpMyAdmin SQL Dump
-- version 4.6.3deb1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 22-12-2016 a las 17:37:52
-- Versión del servidor: 5.5.53-0+deb8u1
-- Versión de PHP: 5.6.29-0+deb8u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tdiw-j5`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Admin`
--

CREATE TABLE `Admin` (
  `id` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `Admin`
--

INSERT INTO `Admin` (`id`, `username`, `password`) VALUES
(1, 'admin', 'd033e22ae348aeb5660fc2140aec35850c4da997');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Category`
--

CREATE TABLE `Category` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `Category`
--

INSERT INTO `Category` (`id`, `name`) VALUES
(1, 'Components'),
(2, 'Accesories'),
(3, 'Computers'),
(4, 'Peripherics'),
(5, 'Smartphones');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Products`
--

CREATE TABLE `Products` (
  `id` int(11) NOT NULL,
  `sub_category_id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `specs` varchar(5000) NOT NULL,
  `characteristics` varchar(5000) NOT NULL,
  `stock` int(11) NOT NULL,
  `price` float NOT NULL,
  `product_image` varchar(200) NOT NULL,
  `brand_image` varchar(200) NOT NULL,
  `brand` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `Products`
--

INSERT INTO `Products` (`id`, `sub_category_id`, `name`, `specs`, `characteristics`, `stock`, `price`, `product_image`, `brand_image`, `brand`) VALUES
(1, 5, 'G.Skill Ripjaws V Red DDR4', '', '', 300, 76, 'images/g-skill-ripjaws-5-red-ddr4-2133-pc4-17000-8gb-2x4gb-cl15.jpg', 'images/gskill.jpg', 'G.Skill'),
(2, 32, 'Huawei Nexus 6P 4G', '', '', 99, 460, 'images/huawei-nexus-6p-4g-plata-libre.jpg', 'images/huawei.jpg', 'Huawei'),
(3, 1, 'MSI 990FXA Gaming', '<li>CPU Supports AMD® FX ™ / Phenom ™ processors II / Athlon ™ II and Sempron ™ for the AM3 / AM3 + socket</li>\r\n                        <li>Chipset AMD® 990FX and SB950</li>\r\n                        <li>Main Memory\r\n                            <ul>\r\n                                <li>4x DDR3 memory slots supporting up to 32 GB</li>\r\n                                <li>Supports DDR3 2133 (OC) / 1866/1600/1333/1066 MHz</li>\r\n                                <li>Memory Architecture Dual Channel</li>\r\n                            </ul>\r\n                        </li>\r\n                        <li>Slots\r\n                            <ul>\r\n                                <li>3x PCIe 2.0 x16\r\n                                    <ul>\r\n                                        <li>PCI_E2, PCI_E5 to support PCIe 2.0 x16 speed</li>\r\n                                        <li>PCI_E4 supports up to PCIe 2.0 x4 speed</li>\r\n                                    </ul>\r\n                                </li>\r\n                                <li>2x PCIe 2.0 x1 slots</li>\r\n                                <li>1 x PCI slot</li>\r\n                            </ul>\r\n                        </li>\r\n                        <li>On-Board SATA AMD® SB950 chipset 6x SATA 6Gb/s • RAID - Supports RAID 0, RAID 1, RAID 5 and RAID 10</li>\r\n                        <li>USB\r\n                            <ul>\r\n                                <li>AMD® SB950 chipset 14x USB 2.0 ports (8 ports on the back panel, 6 ports available through the internal USB 2.0 connectors)</li>\r\n                                <li>VIA ® chipset VL806 2x USB 3.0 ports available through the internal USB 3.0 connector *</li>\r\n                                <li>Asmedia® 1142 chipset 2x USB 3.1 ports on the rear panel</li>\r\n                            </ul>\r\n                        </li>\r\n                        <li>Audio Realtek ALC1150 Codec\r\n                            <ul>\r\n                                <li>7.1 channel High Definition Audio</li>\r\n                                <li>Supports S/PDIF</li>\r\n                            </ul>\r\n                        </li>\r\n                        <li>LAN Gigabit LAN controller Killer E2205 1 x LAN port on the rear panel</li>\r\n                        <li>Internal I/S Connectors\r\n                            <ul>\r\n                                <li>1 x 24-pin ATX main power connector</li>\r\n                                <li>1 x 8-pin ATX 12V power connector</li>\r\n                                <li>6 x SATA connectors 6 Gb/s</li>\r\n                                <li>3 x USB 2.0 connectors (supports additional 6 USB 2.0 ports)</li>\r\n                                <li>1 x USB 3.0 connector</li>\r\n                                <li>1 x CPU fan connector 4-pin</li>\r\n                                <li>2 x 4-pin fan connectors System</li>\r\n                                <li>2 x 3-pin fan connectors System</li>\r\n                                <li>1 x audio jack on the front panel</li>\r\n                                <li>2 x connectors on the front panel</li>\r\n                                <li>1 x chassis intrusion connector</li>\r\n                                <li>1 x connector module TPM</li>\r\n                                <li>1 x connector port Serial</li>\r\n                                <li>1 x S/PDIF Out connector</li>\r\n                                <li>1x Clear CMOS jumper</li>\r\n                                <li>1x mode switch slow start </li>\r\n                            </ul>\r\n                        </li>\r\n\r\n                        <li>Back Panel Ports E/S\r\n                            <ul>\r\n                                <li>1x PS/2 keyboard/mouse port combined</li>\r\n                                <li>8x USB 2.0 ports</li>\r\n                                <li>2x USB 3.1 ports</li>\r\n                                <li>1x Optical S/PDIF Out connector</li>\r\n                                <li>1x LAN (RJ45) port</li>\r\n                                <li>6x OFC audio jacks</li>\r\n                            </ul>\r\n                        </li>\r\n                        <li>Support Multi-GPU\r\n                            <ul>\r\n                                <li>Supports 3-Way Technology CrossFireTM AMD®</li>\r\n                                <li>Supports 2-Way NVIDIA® SLITM Technology</li>\r\n                            </ul>\r\n                        </li>\r\n                        <li>BIOS\r\n                            <ul>\r\n                                <li>64 Mb Flash</li>\r\n                                <li>AMI UEFI BIOS</li>\r\n                                <li>ACPI 5.0, 1.0a PnP, SM BIOS 2.7, DMI 2.0</li>\r\n                                <li>Multi-language</li>\r\n                            </ul>\r\n                        </li>\r\n                        <li>Dimension 30.5 cm x 24.4 cm Form Factor ATX</li>', '<li>Supports AMD FX / Phenom ™ processors II / Athlon ™ II / Sempron ™ for AM3 + socket</li>\r\n                        <li>Supports DDR3-2133 (OC) memory</li>\r\n                        <li>USB 3.1 + SATA 6Gb/s</li>\r\n                        <li>Audio Boost 2: Reward your ears with True Quality</li>\r\n                        <li>Ethernet Killer: Kill Your Lag</li>\r\n                        <li>OC Genie 4: Overclock in 1 second</li>\r\n                        <li>Click BIOS 4: Easily tune your system</li>\r\n                        <li>NVIDIA SLI and AMD CrossFireX Support</li>\r\n                        <li>Sound Blaster Cinema 2: realistic surround sound experience</li>\r\n                        <li>Gaming Device Port: optimized with Triple Gold-plated high vote Rate Game Controllers</li>', 50, 143, 'images/msi-990fxa.jpg', 'images/msi.jpg', 'MSI'),
(4, 15, 'Toshiba Satellite L12-C-104', '', '', 139, 285, 'images/Toshiba-Satellite-L12-C-104.jpg', 'images/toshiba.jpg', 'Toshiba'),
(5, 10, 'Corsair Cooling Hydro Series H', '', '', 199, 84, 'images/corsair-cooling-hydro-series-h75.jpg', 'images/corsair.jpg', 'Corsair'),
(6, 19, 'LG 43LH500T 43" LED', '', '', 119, 295, 'images/lg-32lh500d-32-led.jpg', 'images/lg.jpg', 'LG'),
(7, 3, 'Samsung 750 Evo SSD Series 250', '', '', 158, 70, 'images/samsung-750-evo-ssd-series-120gb-sata3.jpg', 'images/samsung.jpg', 'Samsung'),
(8, 23, 'Corsair K70 Teclado Cherry MX ', '', '', 89, 197, 'images/Corsair-K70.jpg', 'images/corsair.jpg', 'Corsair'),
(9, 1, 'Asus Rampage V Edition 10', '<li>CPU</li>\r\n<ul>\r\n<li>Intel® Core™ i7 X-Series Processors on LGA 2011-v3 Socket</li>\r\n<li>Supports 14nm CPU</li>\r\n<li>Supports Intel® Turbo Boost Max Technology 3.0*</li>\r\n<li>* The support of these features depends on the CPU types.</li>\r\n</ul>\r\n<li>Chipset</li>\r\n<ul>\r\n<li>Intel® X99 Chipset</li>\r\n</ul>\r\n<li>Memory</li>\r\n<ul>\r\n<li>8 x DIMM, max. 128GB, DDR4, non-ECC, un-buffered memory</li>\r\n<li>Quad channel memory architecture</li>\r\n<li>Supports Intel® Extreme Memory Profile (XMP)</li>\r\n<li>* Hyper DIMM support is subject to the physical characteristics of individual CPUs. Please refer to Memory QVL (Qualified Vendors List) for details.</li>\r\n</ul>\r\n<li>Expansion Slots</li>\r\n<ul>\r\n<li>4 x PCIe 3.0 x16 slots</li>\r\n<li>1 x PCIe 2.0 x4 slot** [PCH]</li>\r\n<li>1 x PCIe 2.0 x1 slot [PCH]</li>\r\n<li>* The PCIE_X8_4 slot shares bandwidth</li>\r\n<li>** The PCIE_X4_1 slot shares bandwidth with front USB3_34 ports and back USB3.1_EC1_EA2 ports<br>\r\nIf a X2 device is connected to the PCIEx4_1 slot, the front USB3_34 ports will be disabled.<br>\r\nIf a X4 or higher device is connected to the PCIEx4_1 slot, both front USB3_34 and back USB3.1_EC1_EA2 ports will be disabled.</li>\r\n</ul>\r\n<li>Multi-GPU Support</li>\r\n<ul>\r\n<li>Supports NVIDIA® 4-Way/3-Way/Quad-GPU SLI™ Technology*</li>\r\n<li>Supports AMD® 4-Way/3-Way/Quad-GPU CrossFireX™ Technology*</li>\r\n<li>* 28-LANE CPUs can only support up to 3-Way SLI™/ 3-Way CrossFireX™</li>\r\n</ul>\r\n<li>Storage</li>\r\n<ul>\r\n<li>New Intel® Core™ i7 Processors</li>\r\n<li>1 x M.2 PCIe 3.0 x4 Socket 3 with M Key, type 2260/2280/22110 (supports PCIe storage device only)*</li>\r\n<li>1 x U.2 port (support PCIe 3.0 x4 NVM Express storage)*</li>\r\n<li>* These ports share bandwidth with PCIEX8_4 slot</li>\r\n<li>Intel® X99 Chipset with RAID 0, 1, 5, 10 and Intel Rapid Storage Technology 14 support</li>\r\n<li>10 x SATA 6Gb/s ports</li>\r\n<li>Supports Intel® Smart Response Technology**</li>\r\n<li>** These functions will work depending on the CPU installed.</li>\r\n</ul>\r\n<li>LAN</li>\r\n<ul>\r\n<li>Gigabit Intel® LAN connection - 802.3az Energy Efficient Ethernet (EEE) appliance</li>\r\n<li>Intel® I218-V Gigabit LAN- Dual interconnect between the integrated Media Access Controller (MAC) and physical layer (PHY)</li>\r\n<li>Intel® I211-AT Gigabit LAN controller</li>\r\n<li>Anti-surge LANGuard</li>\r\n<li>ROG GameFirst Technology</li>', '     <li>Intel® Core™ i7 processors on LGA 2011-v3 socket</li>\r\n     <li>Quad-channel DDR4 3333 (OC) support</li>\r\n     <li>Best gaming lighting: Aura RGB illumination and Aura 4-pin RGB-strip header </li>\r\n     <li>Best gaming extra – SupremeFX Hi-Fi</li>\r\n     <li>Best gaming performance – 5-Way Optimization with Auto-Tuning, second-generation T-Topology and Extreme Engine Digi+</li>\r\n     <li>Best gaming audio – Reinvented SupremeFX with intuitive Sonic Studio II</li>\r\n     <li>Best gaming networking – Best-in-class Intel® Gigabit Ethernet ,LANGuard and GameFirst technology</li>\r\n     <li>Best gaming connectivity — 3x3 802.11ac Wi-Fi , NVMe U.2, M.2, SATA and USB 3.1 Type-A and Type-C for extreme-speed transfers and total compatibility</li>\r\n     <li>Best gaming protection – SafeSlot, pre-mounted I/O shield, and carefully-selected premium components for maximum durability.</li>\r\n     <li>ROG gives you more – More gaming-focused utilities, all free!</li>', 108, 612, 'images/asus-rampage-v-edition-10.jpg', 'images/asus.jpg', 'Asus'),
(10, 1, 'MSI X99A Gaming Pro Carbon', '<li> CPU\r\n<ul>\r\n<li> Supports New Intel® Core ™ i7 Processor Extreme Edition for LGA2011-3 Socket </li>\r\n<li> * This function will be supported depending on the CPU. </li>\r\n</ul>\r\n</li>\r\n<li> Chipset\r\n<ul>\r\n<li> • Intel® X99 Chipset </li>\r\n</ul>\r\n</li>\r\n<li> Main Memory\r\n<ul>\r\n<li> 8 x DDR4 memory slots, support up to 128GB * </li>\r\n<li> Supports DDR4 3466 (OC) / 3400 (OC) / 3333 (OC) / 3200 (OC) / 3000 (OC) / 2933 (OC) / 2800 (OC) / 2666 (OC) / 2600 (OC) / 2400/2200 (OC) / 2133 MHz </li>\r\n<li> Quad channel memory architecture </li>\r\n<li> Supports Intel® Extreme Memory Profile (XMP) </li>\r\n<li> * For the latest information about memory, please visit http://www.msi.com </li>\r\n</ul>\r\n</li>\r\n<li> Slots\r\n<ul>\r\n<li> 4x PCIe 3.0 x16 slots * </li>\r\n<li> Support x16 / x0 / x0 / x0, x16 / x16 / x0 / x0, x16 / x16 / x0 / x8, x8 / x16 / x8 / x8 with the CPU that supports 40 PCIe lanes.\r\n<li> Support x16 / x0 / x0 / x0, x16 / x8 / x0 / x0, x8 / x8 / x8 / x0, x8 / x8 / x8 / x4 with the CPU that supports 28 PCIe lanes.\r\n<li> 2 x PCIe 2.0 x1 slots </li>\r\n<li> * PCI_E6 slot shares the bandwidth with U.2 port and M.2 PCIe 3.0 x4. Please refer to page 32 for PCIe 3.0 bandwidth table. </li>\r\n</ul>\r\n</li>\r\n<li> Multi-GPU\r\n<ul>\r\n<li> Supports 3-Way AMD® CrossFire ™ Technology </li>\r\n<li> Supports 3-Way NVIDIA® SLI ™ Technology </li>\r\n</ul>\r\n</li>\r\n<li> Storage\r\n<ul>\r\n<li> Intel® X99 Chipset </li>\r\n<li> 10 x SATA 6Gb / s ports (2 ports from SATAe port) </li>\r\n<li> SATA1 ~ 6 support RAID 0, RAID 1, RAID 5 and RAID 10 </li>\r\n<li> SATA7 ~ 10 only support IDE mode and AHCI mode </li>\r\n<li> 1 x M.2 slot (Key M) * </li>\r\n<li> Supports up to PCIe 3.0 x4 and SATA 6Gb / s </li>\r\n<li> Supports 2242/2260/2280 storage devices </li>\r\n<li> 1 x U.2 port * / ** </li>\r\n<li> Supports PCIe 3.0 x4 NVMe storage </li>\r\n<li> 1 x SATA port * (compatible with 2 SATA ports) </li>\r\n<li> Supports up to PCIe 2.0x2 </li>\r\n<li> Supports Intel® Smart Response Technology *** </li>\r\n<li> <font size = "1"> * M.2 slot (PCIe 3.0 x4) and U.2 share the same bandwidth. Please refer to page 38 for U.2 / M.2 / SATAe & amp; SATA combination table. </ Font> </li>\r\n<li> <font size = "1"> ** The U.2 port will be unavailable when installing the PCIe device in PCI_E6 slot. </ Font> </li>\r\n<li> <font size = "1"> *** This function will be supported depending on the CPU. </ Font> </li>\r\n</ul>\r\n</li>\r\n<li> USB\r\n<ul>\r\n<li> ASMedia® ASM1142 Chipset </li>\r\n<li> 1 x USB 3.1 Gen2 (SuperSpeed ​​USB 10Gbps) port on the back panel </li>\r\n<li> 1 x USB 3.1 Gen2 (Super Speed ​​USB 10Gbps) Type-C port on the back panel </li>\r\n<li> Intel® X99 Chipset </li>\r\n<li> 5 x USB 3.1 Gen1 (SuperSpeed ​​USB) ports (1 internal Type-C port on the board, 4 ports available through the internal USB connectors) </li>\r\n<li> 8 x USB 2.0 (High-speed USB) ports (4 ports on the back panel, 4 ports available through the internal USB connectors) </li>\r\n<li> VIA VL805 Chipset </li>\r\n<li> 4 x USB 3.1 Gen1 (SuperSpeed ​​USB) ports on the back panel </li>\r\n<li> & nbsp; </li>\r\n</ul>\r\n</li>\r\n<li> Audio\r\n<ul>\r\n<li> Realtek® ALC1150 Codec </li>\r\n7.1-Channel High Definition Audio </li>\r\n<li> Supports S / PDIF output </li>\r\n</ul>\r\n</li>\r\n<li> LAN\r\n<ul>\r\n<li> 1 x Intel® I218-V Gigabit LAN controller </li>\r\n</ul>\r\n</li>\r\n<li> Internal I / O Connectors\r\n<ul>\r\n<li> 1 x 24-pin ATX main power connector </li>\r\n<li> 1 x 8-pin ATX 12V power connector </li>\r\n<li> 10 x SATA 6Gb / s connectors </li>\r\n<li> 1 x SATA Express connector </li>\r\n<li> 1 x M.2 slot </li>\r\n<li> 1 x U.2 port </li>\r\n<li> 2 x USB 2.0 connectors (supports additional 4 USB 2.0 ports) </li>\r\n<li> 2 x USB 3.1 Gen1 connectors (supports additional 4 USB 3.1 Gen1 ports) </li>\r\n<li> 1 x USB 3.1 Gen1 Type-C port </li>\r\n<li> 1 x 4-pin CPU fan connector </li>\r\n<li> 1 x 4-pin Water Pump connector </li>\r\n<li> 3 x 4-pin system fan connectors </li>\r\n<li> 1 x Front panel audio connector </li>\r\n<li> 2 x Front panel connectors </li>\r\n<li> 1 x TPM module connector </li>\r\n<li> 1 x Chassis Intrusion connector </li>\r\n<li> 1 x Clear CMOS jumper </li>\r\n<li> 1 x Slow mode booting jumper </li>\r\n<li> 1 x GAME BOOST knob </li>\r\n<li> 1 x Power button </li>\r\n<li> 1 x Reset button </li>\r\n<li> 1 x Multi-BIOS switch </li>\r\n<li> 1 x RGB LED connector </li>\r\n</ul>\r\n</li>\r\n<li> Back Panel I / O Ports\r\n<ul>\r\n<li> 1 x PS / 2 keyboard / mouse combo port </li>\r\n<li> 4 x USB 2.0 ports </li>\r\n<li> 1 x Clear CMOS button </li>\r\n<li> 4 x USB 3.1 Gen1 ports </li>\r\n<li> 1 x LAN (RJ45) port </li>\r\n<li> 1 x USB 3.1 Gen2 port </li>\r\n<li> 1 x USB 3.1 Gen2 Type-C port </li>\r\n<li> 1 x Optical S / PDIF OUT connector & nbsp; </li>\r\n<li> 5 x OFC audio jacks </li>\r\n</ul>\r\n</li>\r\n<li> Dimension\r\n<ul>\r\n<li> 30.5 cm x 24.4 cm </li>\r\n<li> ATX Form Factor </li>\r\n</ul>\r\n</li>\r\n<li> Mounting\r\n<ul>\r\n<li> 9 mounting holes </li>\r\n</ul>\r\n</li>', '<li> Supports New Intel® Core ™ i7 Processor Extreme Edition for LGA 2011-3 socket </li>\r\n<li> DDR4 Steel Armor with Best Signal Stability, Quad Channel DDR4-3466 + (OC) </li>\r\n<li> Mystic Light: Customize and set your own color scheme with GAMING APP to make your PC look & amp; Feel brand new! </li>\r\n<li> Mystic Light Extension: Extend you style to everything </li>\r\n<li> Turbo U.2 & amp; M.2 Steel Armor: Delivering next generation M2 Gen3 x4 performance with transfer speeds up to 32 Gb / s </li>\r\n<li> USB 3.1 Gen2 Type-C & amp; Type-A combo + USB 3.1 Gen 1 Type-C (front) + SATA EXPRESS 10Gb / s + SATA 6Gb / s </li>\r\n<li> Audio Boost 3: Reward your ears with studio grade sound quality </li>\r\n<li> Nahimic Audio Enhancer: Immersive sound quality that pushes the limits of reality </li>\r\n<li> GAMING LAN with LAN Protect, powered by Intel®: The best online gaming experience with lowest latency </li>\r\n<li> XSplit Gamecaster V2: Show off your skills to the world with a 1 year premium license for this popular streaming software </li>\r\n<li> Military Class 5: The latest evolution in high quality components featuring the brand new Titanium Chokes </li>\r\n<li> Guard-Pro: Improved Protection and Power Efficiency </li>\r\n<li> MULTI-GPU with Steel Armor: Steel Armor PCI-E slots. Supports 3-way NVIDIA SLI & amp; AMD Crossfire ™ </li>\r\n<li> Game Boost: Generate more FPS in games with 1 push of a button </li>\r\n<li> EZ Debug LED: Easiest way to troubleshoot </li>\r\n<li> Overvoltage Protection: Prevent Unforeseen Damage </li>\r\n<li> GAMING Hotkey: Launch your favorite games with a single button </li>\r\n<li> Click BIOS 5: Award-winning brand new Click BIOS 5 with high resolution scalable font </li>\r\n<li> SteelSeries Certified: Optimized for SteelSeries gaming gear </li>\r\n<li> GAMING CERTIFIED: 24-hour online and offline game testing by eSports players for the best gaming experience </li>', 170, 332, 'images/msi-x99a-gaming-pro-carbon.jpg', 'images/msi.jpg', 'MSI'),
(11, 1, 'Gigabyte GA-X99-GAMING 5P', '<li> Processor\r\n<ul>\r\n<li> Support for Intel® Core ™ i7 processors socket LGA2011-3 </li>\r\n<li> L3 cache varies by CPU </li>\r\n<li> Please refer to the "Supported CPU List" for more information </li>\r\n</ul>\r\n</li>\r\n<li> Chipset\r\n<ul>\r\n<li> Intel® X99 Express Chipset </li>\r\n</ul>\r\n</li>\r\n<li> Memory\r\n<ul>\r\n<li> Support for DDR4 3333 / 2133 MHz memory modules </li>\r\n<li> Architecture for 4 memory channels </li>\r\n<li> Support for non-ECC memory modules </li>\r\n<li> Support for RDIMM 1Rx8 / 2Rx8 / 1Rx4 / 2Rx4 memory modules</li>\r\n<li> Extreme Memory Profile (XMP) memory module support </li>\r\n<li> 8 x DDR4 sockets supporting up to 128 GB of system memory </li>\r\n</ul>\r\n</li>\r\n<li> Audio\r\n<ul>\r\n<li> High Definition Audio </li>\r\n<li> Support for S / PDIF output </li>\r\n<li> Supports Sound Blaster Recon3Di </li>\r\n<li> Channels 2 / 5.1 </li>\r\n<li> Creative Sound® 3D Sound Chip </li>\r\n<li> Burr Brown® OPA2134 Operational Amplifier </li>\r\n</ul>\r\n</li>\r\n<li> LAN\r\n<ul>\r\n<li> Qualcomm® Atheros Killer E2201 LAN (10/100/1000 Mbit) Chip </li>\r\n</ul>\r\n</li>\r\n<li> Expansion Sockets\r\n<ul>\r\n<li> 1 x M.2 Socket 1 connector for the wireless communication module (M2_WIFI) </li>\r\n<li> 2 x PCI Express x16 slots, running at x16 (PCIE_1, PCIE_2) </li>\r\n<li> 2 x PCI Express x16 slots, running at x8 (PCIE_3, PCIE_4) </li>\r\n<li> 3 x PCI Express x1 slots </li>\r\n</ul>\r\n</li>\r\n<li> Multi Graphics Technology\r\n<ul>\r\n<li> Supports AMD CrossFire ™ / NVIDIA® SLI ™ 4-Way / 3-Way / 2-Way Technologies </li>\r\n</ul>\r\n</li>\r\n<li> Storage interface\r\n<ul>\r\n<li> Chipsets:\r\n<ul>\r\n<li> 1 x M.2 PCIe connector </li>\r\n<li> 1 x SATA Express connector </li>\r\n<li> 6 x SATA connectors at 6Gb / s (SATA3 0 ~ 5) </li>\r\n<li> Support for RAID 0, RAID 1, RAID 5, and RAID 10 </li>\r\n<li> 4 x SATA Connectors 6Gb / s sSATA3 0 ~ 3), IDE and AHCI mode support </li>\r\n</ul>\r\n</li>\r\n</ul>\r\n</li>\r\n<li> USB\r\n<ul>\r\n<li> Chipsets:\r\n<ul>\r\n<li> 8 x USB 2.0 / 1.1 ports</li>\r\n<li> 4 x USB 3.0 / 2.0 port</li>\r\n</ul>\r\n</li>\r\n<li> Hub Chipset + USB 3.0 Renesas® uPD720210:\r\n<ul>\r\n<li> 4 x USB 3.0 / 2.0 rear connectors </li>\r\n</ul>\r\n</li>\r\n</ul>\r\n</li>\r\n<li> Internal I / O Connectors\r\n<ul>\r\n<li> 1 x CPU Mode Selector </li>\r\n<li> 1 x USB 3.0 / 2.0 </li>\r\n<li> 1 x ATX 12V 8-pin power connector </li>\r\n<li> 3 x system fan connector </li>\r\n<li> 1 x CPU fan connector </li>\r\n<li> 1 x front panel connector </li>\r\n<li> 1 x ATX 24-pin main power connector </li>\r\n<li> 1 x audio connector on the front panel </li>\r\n<li> 2 x USB 2.0 / 1.1 connectors </li>\r\n<li> 1 x power LED heatsink connector </li>\r\n<li> 1 x PCIe power connector </li>\r\n<li> 1 x Clear CMOS jumper </li>\r\n<li> 1 x Water-cooled fan connector (CPU_OPT) </li>\r\n<li> 10 x 6Gb / s SATA connector </li>\r\n<li> 1 x audio gain control switch </li>\r\n<li> 1 x SATA Express connector </li>\r\n<li> 1 x I / O shield audio LED power connector </li>\r\n<li> 1 x Triple M.2 socket connector </li>\r\n<li> 1 x Thunderbolt ™ expansion card </li>\r\n</ul>\r\n</li>\r\n<li> Rear I / O Panel\r\n<ul>\r\n<li> 2 x Wi-Fi antenna connector hole </li>\r\n<li> 4 x USB 2.0 / 1.1 ports </li>\r\n<li> 1 x Optical S / PDIF Out connector </li>\r\n<li> 6 x USB 3.0 / 2.0 port </li>\r\n<li> 1 x PS / 2 mouse port </li>\r\n<li> 1 x PS / 2 Keyboard port </li>\r\n<li> 2 x RJ-45 port </li>\r\n<li> 5 x audio jacks</li>\r\n</ul>\r\n</li>\r\n<li> I / O Controller\r\n<ul>\r\n<li> iTE® I / O Controller Chip </li>\r\n</ul>\r\n</li>\r\n<li> Hardware Monitoring\r\n<ul>\r\n<li> System Voltage Detection </li>\r\n<li> * The fan speed control function is supported whenever it supports it. </li>\r\n<li> Temperature Detection for CPU / System / Chipset </li>\r\n<li> Fan speed detection CPU / CPU OPT / System </li>\r\n<li> CPU / CPU OPT / System fan failure warning </li>\r\n<li> Fan speed control CPU / CPU OPT / System </li>\r\n<li> CPU / System / Chipset Overheat Alert </li>\r\n</ul>\r\n</li>\r\n<li> BIOS\r\n<ul>\r\n<li> Supports DualBIOS ™ </li>\r\n<li> 2 x 128 Mbit flash </li>\r\n<li> AMI UEFI BIOS License </li>\r\n<li> PnP 1.0a, DMI 2.7, WfM 2.0, SM BIOS 2.7, ACPI 5.0 </li>\r\n<li> Q-Flash Plus support </li>\r\n</ul>\r\n</li>\r\n<li> Other Features\r\n<ul>\r\n<li> Support for Q-Flash </li>\r\n<li> Supports Xpress Install </li>\r\n<li> Support for APP Center </li>\r\n<li> Supports Smart Switch </li>\r\n<li> @ BIOS ™ </li>\r\n<li> Cloud Station Support </li>\r\n<li> Ambient LEDs </li>\r\n<li> V-Tuner </li>\r\n<li> EasyTune support </li>\r\n<li> Support for On / Off Charge </li>\r\n<li> Supports EZ Setup </li>\r\n<li> Supports Smart Recovery 2 </li>\r\n<li> USB Blocker </li>\r\n<li> Smart TimeLock </li>\r\n<li> Fast Boot </li>\r\n<li> System Information Viewer </li>\r\n<li> Game Controller </li>\r\n</ul>\r\n</li>\r\n<li> Included Software\r\n<ul>\r\n<li> Intel® Smart Response Technology </li>\r\n<li> Norton® Internet Security (OEM version) </li>\r\n</ul>\r\n</li>\r\n<li> Operating System\r\n<ul>\r\n<li> Support for Windows 8.1 / 8/7 </li>\r\n</ul>\r\n</li>\r\n<li> Format\r\n<ul>\r\n<li> E-ATX form factor: 30.5cm x 26.4cm </li>\r\n</ul>\r\n</li>\r\n</ul>', '<li> Supports the new Intel® Core ™ i7 Extreme Edition processor </li>\r\n<li> SATA Express support for 10Gb / s data transfers </li>\r\n<li> Killer ™ E2200 for network games </li>\r\n<li> Proprietary Digital Power System with Powrstage® IRs Digital IR PWM and IRs </li>\r\n<li> 4-way graphics with Premium PCIe Lane </li>\r\n<li> Dual M.2 technology for SSD and WIFI cards </li>\r\n<li> Stand-alone PCB layers for left and right audio channels </li>\r\n<li> Led lighting for audio separation and rear panel led </li>\r\n<li> Long life Durable Black ™ solid capacitors </li>\r\n<li> Thunderbolt ™ Ready </li>\r\n<li> Gold-plated audio ports </li>\r\n<li> Supports DDR4 XMP up to 3333MHz </li>\r\n<li> GIGABYTE DualBIOS ™ UEFI with USB port Q-Flash Plus </li>\r\n<li> DAC-UP Quad USB ports </li>\r\n<li> Creative Sound Core3D ™ quad-core integrated audio processor </li>\r\n<li> AMP-UP audio technology with exclusive upgradeable OP-AMP </li>\r\n<li> High-end Nichicon audio capacitors </li>\r\n<li> APP Center, which includes the EasyTune ™ and Cloud Station ™ utilities </li>\r\n<li> Gold plating on the CPU socket, memory DIMMs and PCIe slots </li>', 80, 305, 'images/gigabyte-ga-x99-gaming-5p.jpg', 'images/gigabyte.jpg', 'Gigabyte'),
(12, 1, 'Asus Z170 Deluxe', '<li>CPU</li>\r\n<ul>\r\n<li>Intel® Socket 1151 for 6th Generation Core™ i7/Core™ i5/Core™ i3/Pentium®/Celeron® Processors</li>\r\n<li>Supports Intel® 14 nm CPU</li>\r\n<li>Supports Intel® Turbo Boost Technology 2.0\r\n<li>* The Intel® Turbo Boost Technology 2.0 support depends on the CPU types.</li>\r\n</ul>\r\n<li>Chipset</li>\r\n<ul>\r\n<li>Intel® Z170</li>\r\n</ul>\r\n<li>Memory</li>\r\n<ul>\r\n<li>4 x DIMM, Max. 64GB, DDR4 Non-ECC, Un-buffered Memory</li>\r\n<li>Dual Channel Memory Architecture</li>\r\n<li>Supports Intel® Extreme Memory Profile (XMP)</li>\r\n<li>* Hyper DIMM support is subject to the physical characteristics of individual CPUs</li>\r\n</ul>\r\n<li>Graphic</li>\r\n<ul>\r\n<li>Integrated Graphics Processor- Intel® HD Graphics support</li>\r\n<li>Multi-VGA output support : HDMI/DisplayPort ports</li>\r\n<li>Supports HDMI with max. resolution 4096  x 2160  @ 24 Hz / 4096  x 2160  @ 60 Hz</li>\r\n<li>Supports DisplayPort with max. resolution 4096  x 2304  @ 24 Hz / 4096  x 2304  @ 60 Hz</li>\r\n<li>Maximum shared memory of 512MB</li>\r\n<li>Supports Intel® InTru™ 3D, Quick Sync Video, Clear Video HD Technology, Insider™</li>\r\n<li>Supports up to 2 displays simultaneously</li>\r\n<li>DP 1.2 Multi-Stream Transport compliant, supports DP 1.2 monitor daisy chain up to 3 displays</li>\r\n</ul>\r\n<li>Multi-GPU Support</li>\r\n<ul>\r\n<li>Supports NVIDIA® Quad-GPU SLI™ Technology</li>\r\n<li>Supports NVIDIA® 2-Way SLI™ Technology</li>\r\n<li>Supports AMD Quad-GPU CrossFireX™ Technology</li>\r\n<li> Supports AMD 3-Way CrossFireX™ Technology</li>\r\n</ul>\r\n<li>Expansion Slots</li>\r\n<ul>\r\n<li>2 x PCIe 3.0/2.0 x16 (x16 or dual x8)</li>\r\n<li>1 x PCIe 3.0/2.0 x16 (max at x4 mode)*1</li>\r\n<li>4 x PCIe x1</li>\r\n</ul>\r\n<li>Storage</li>\r\n<ul>\r\n<li>1 x SATA Express port, Compatible with 2 x SATA 6.0 Gb/s ports</li>\r\n<li>1 x M.2 x4 Socket 3, with M Key, type 2242/2260/2280/22110 storage devices support (both SATA &amp; PCIE mode)*2</li>\r\n<li>6 x SATA 6Gb/s port(s), gray<li> Support Raid 0, 1, 5, 10</li>\r\n<li>Intel® Rapid Storage Technology supports*3</li>\r\n<li>Supports Intel® Smart Response Technology*4</li>\r\n<li><strong>ASMedia® SATA 6Gb/s controller : </strong>*5</li>\r\n<li>2 x SATA 6Gb/s port(s)</li>\r\n</ul>\r\n<li>LAN</li>\r\n<ul>\r\n<li>Intel® I219V, 1 x Gigabit LAN Controller(s)</li>\r\n<li> Intel® I211-AT, 1 x Gigabit LAN<li> Gigabit Intel® LAN Connection- 802.3az Energy Efficient Ethernet (EEE) appliance</li>\r\n<li> ASUS Turbo LAN Utility</li>\r\n<li> ASUS LAN Guard</li>\r\n</ul>\r\n<li>Wireless Data Network</li>\r\n<ul>\r\n<li>Wi-Fi 802.11 a/b/g/n/ac</li>\r\n<li>Supports dual band frequency 2.4/5 GHz</li>\r\n<li>Support ASUS Wi-Fi Go! Utility</li>\r\n<li>Up to 1300Mbps transfer speed</li>\r\n</ul>\r\n<li>Bluetooth</li>\r\n<ul>\r\n<li>Bluetooth V4.0</li>\r\n</ul>', '<ul>\r\n<li>5-Way Optimization: One click optimizes your entire system with a dedicated water-pump header on board</li>\r\n<li>ASUS Pro Clock Technology: Extends base clocks and improves stability for extreme overclocking</li>\r\n<li>6 USB 3.1 onboard: 5 Type-A ports and 1 reversible Type-C port</li>\r\n<li>Dual 32Gbit/s ultra-fast M.2 x4: Onboard and PCIe add-in card</li>\r\n<li>Hyper Kit: Bundled for instant U.2 support</li>\r\n</ul>', 188, 299, 'images/asus-z170-deluxe.jpg', 'images/asus.jpg', 'Asus'),
(13, 3, 'Seagate Barracuda 7200.14 1TB ', '', '', 350, 47, 'images/seagate-barracuda-7200-14-1tb-sata3-1.jpg', 'images/seagate.jpg', 'Seagate'),
(14, 3, 'Avexir E100 SSD 120GB SATA3', '', '', 250, 48, 'images/avexir-ssd-e100-120gb-2-5.jpg', 'images/avexir.jpg', 'Avexir'),
(21, 2, 'Intel i7-6700K 4.0Ghz Box', '<li> Skylake Microarchitecture </li>\r\n<li> Skylake-S processor base </li>\r\n<li> Manufacturing process 0.014 microns </li>\r\n<li> 64 bit data width </li>\r\n<li> Number of cores 4 </li>\r\n<li> Number of threads 8 </li>\r\n<li> Integrated Floating Point Unit </li>\r\n<li> Level 1 cache size\r\n<ul>\r\n<li> 4 x 32 KB instruction & nbsp; </li>\r\n<li> 4 x 32 KB KB data caches </li>\r\n</ul>\r\n</li>\r\n<li> Level 2 cache size 4 x 256 KB caches </li>\r\n<li> Level 3 Cache Size 8 MB shared cache </li>\r\n<li> Monoprocessor Multiprocessing </li>\r\n<li> Features\r\n<ul>\r\n<li> MMX instructions </li>\r\n<li> SSE / Streaming SIMD Extensions </li>\r\n<li> SSE2 / Streaming SIMD Extensions 2 </li>\r\n<li> SSE3 / Streaming SIMD Extensions 3 </li>\r\n<li> SSSE3 / Supplemental Streaming SIMD Extensions 3 </li>\r\n<li> SSE4 / SSE4.1 + SSE4.2 / Streaming SIMD Extensions 4? </li>\r\n<li> AES / Advanced Encryption Standard instructions </li>\r\n<li> AVX / Advanced Vector Extensions </li>\r\n<li> AVX2 / Advanced Vector Extensions 2.0 </li>\r\n<li> BMI / BMI1 + BMI2 / Bit Manipulation instructions </li>\r\n<li> F16C / 16-bit Floating-Point conversion instructions </li>\r\n<li> FMA3 / 3-operand Fused Multiply-Add instructions </li>\r\n<li> EM64T / Extended Memory 64 technology / Intel 64? </li>\r\n<li> NX / XD / Execute disable bit? </li>\r\n<li> HT / Hyper-Threading technology? </li>\r\n<li> TBT 2.0 / Turbo Boost technology 2.0? </li>\r\n<li> VT-x / Virtualization technology? </li>\r\n<li> Low power features Enhanced SpeedStep technology? </li>\r\n</ul>\r\n</li>\r\n<li> Integrated Peripherals / Components <br>\r\n<ul>\r\n<li> Integrated graphics GPU type: Gen 9 LP </li>\r\n<li> Memory controller\r\n<ul>\r\n<li> The number of drivers: 1 </li>\r\n<li> memory channels: 2 </li>\r\n<li> Compatible memory: DDR3L-1333, DDR3L-1600, DDR4-1866, DDR4-2133 </li>\r\n<li> DIMM per channel: 2 </li>\r\n<li> Maximum memory bandwidth (GB / s): 34.1 </li>\r\n</ul>\r\n</li>\r\n</ul>\r\n</li>\r\n<li> Other peripherals\r\n<ul>\r\n<li> Direct Media Interface 3.0 </li>\r\n<li> PCI Express 3.0 interface (16 lanes) </li>\r\n</ul>\r\n</li>\r\n<li> Electrical / thermal parameters\r\n<ul>\r\n<li> Thermal design power 95 watts </li>\r\n</ul>\r\n</li>', '<li> i7-6700K Model </li>\r\n<li> Frequency 4000 MHz </li>\r\n<li> Frequency Turbo 4200 MHz </li>\r\n<li> Bus speed 8 GT / s DMI </li>\r\n<li> Clock & nbsp; 40 </li>\r\n<li> Socket 1151-land Flip-Chip Land Grid Array </li>\r\n<Li> Size & nbsp; 3.75 cm x 3.75 cm </ li>', 299, 334, 'images/intel-i7-6700k.jpg', 'images/intel.jpg', 'Intel'),
(22, 10, 'Corsair Test Fan', '<p>Test Specifications </p>', '', 9, 10, 'images/corsair-fan.jpg', 'images/corsair-logo.png', 'Corsair'),
(23, 10, 'New Corsair Fan', '', '', 50, 5, 'images/corsair-cooling-hydro-series-h75.jpg', 'images/corsair.jpg', 'Corsair');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Sales`
--

CREATE TABLE `Sales` (
  `reference_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `price` float NOT NULL,
  `date` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `Sales`
--

INSERT INTO `Sales` (`reference_id`, `user_id`, `price`, `date`) VALUES
(13, 4, 657, '11-12-2016 17:58:56'),
(14, 4, 536, '11-12-2016 18:15:57'),
(15, 4, 451, '11-12-2016 18:19:31'),
(31, 4, 1224, '20-12-2016 23:42:43'),
(32, 4, 883, '21-12-2016 12:30:39'),
(33, 5, 716, '21-12-2016 12:47:19');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Sales_has_Products`
--

CREATE TABLE `Sales_has_Products` (
  `id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `sales_reference_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `Sales_has_Products`
--

INSERT INTO `Sales_has_Products` (`id`, `product_id`, `sales_reference_id`, `quantity`) VALUES
(11, 2, 13, 1),
(12, 8, 13, 1),
(13, 1, 14, 1),
(14, 2, 14, 1),
(15, 7, 15, 1),
(16, 21, 15, 1),
(17, 13, 15, 1),
(36, 9, 31, 2),
(37, 4, 32, 1),
(38, 12, 32, 2),
(39, 8, 33, 1),
(40, 7, 33, 2),
(41, 6, 33, 1),
(42, 5, 33, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Sub_Category`
--

CREATE TABLE `Sub_Category` (
  `id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `Sub_Category`
--

INSERT INTO `Sub_Category` (`id`, `category_id`, `name`) VALUES
(1, 1, 'Motherboards'),
(2, 1, 'Processors'),
(3, 1, 'Hard Drives'),
(4, 1, 'Graphics Cards'),
(5, 1, 'RAM Memory'),
(6, 1, 'Recorders DVD/Blu-Ray'),
(7, 1, 'Floppy Disks'),
(8, 1, 'Sound Cards'),
(9, 1, 'Boxes'),
(10, 1, 'Fans'),
(11, 1, 'Power Supplies'),
(12, 2, 'Gamepads'),
(13, 2, 'Joysticks'),
(14, 2, 'Microphones'),
(15, 3, 'Laptops'),
(16, 3, 'Ultrabooks'),
(17, 3, 'Desktop'),
(18, 3, 'All in one'),
(19, 4, 'Monitors'),
(20, 4, 'Printers'),
(21, 4, '3D Printers'),
(22, 4, 'Speakers'),
(23, 4, 'Keyboards'),
(24, 4, 'Mouses'),
(25, 4, 'Headphones'),
(26, 4, 'Webcams'),
(27, 5, 'Apple'),
(28, 5, 'Asus'),
(29, 5, 'Bq'),
(30, 5, 'Honor'),
(31, 5, 'HTC'),
(32, 5, 'Huawei'),
(33, 5, 'LG'),
(34, 5, 'Meizu'),
(35, 5, 'Motorola'),
(36, 5, 'Nokia'),
(37, 5, 'Samsung'),
(38, 5, 'Sony'),
(39, 5, 'Xiaomi'),
(40, 5, 'ZTE'),
(41, 5, 'Acer');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Users`
--

CREATE TABLE `Users` (
  `id` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(60) NOT NULL,
  `email` varchar(50) NOT NULL,
  `street` varchar(50) NOT NULL,
  `city` varchar(30) NOT NULL,
  `phone` int(11) NOT NULL,
  `postal_code` int(5) NOT NULL,
  `credit_card` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `Users`
--

INSERT INTO `Users` (`id`, `name`, `username`, `password`, `email`, `street`, `city`, `phone`, `postal_code`, `credit_card`) VALUES
(3, 'Pepe Nito DelBosque', 'pepito2', '12345678', 'email@gmail.com', 'Una Cualquiera', 'Una Cualquiera', 912345678, 1234, '2147483647'),
(4, 'Pepe Del Bosque', 'pepito3', '7c222fb2927d828af22f592134e8932480637c0d', 'email@gmail.com', 'medaigual', 'medaigual', 123456788, 11111, '1234567890123456'),
(5, 'Hector', 'evasion', '7c222fb2927d828af22f592134e8932480637c0d', 'hector.dearmas@e-campus.uab.cat', 'cvdkjfdljf', 'sdjksbhxcjksx', 123456789, 12345, '1234567891234567'),
(7, 'adsfkad 1341234', 'alumne', 'a46b5e1c796f66fa1820b1d1fa4ac4d28bd12182', 'a@b.com', 'adfasdf', 'adasfsdf', 111111111, 813, '1234567890123456'),
(8, 'juanola', 'juanola', 'f7c3bc1d808e04732adf679965ccc34ca7ae3441', 'juanola@error.es', 'aqui', 'test', 123456789, 456, '1234567878945612'),
(9, 'Hector', 'hectito18', '7c222fb2927d828af22f592134e8932480637c0d', 'hector.de.armas@hotmail.com', 'street', 'city', 111111111, 12345, '1111111111111111'),
(10, 'hector', 'prueba_1', '7c222fb2927d828af22f592134e8932480637c0d', 'prueba@prueba.com', 'prueba', 'prueba', 111111111, 11111, '2222222222222222');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Admin`
--
ALTER TABLE `Admin`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Category`
--
ALTER TABLE `Category`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Products`
--
ALTER TABLE `Products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sub_category_id` (`sub_category_id`);

--
-- Indices de la tabla `Sales`
--
ALTER TABLE `Sales`
  ADD PRIMARY KEY (`reference_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indices de la tabla `Sales_has_Products`
--
ALTER TABLE `Sales_has_Products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `sales_reference_id` (`sales_reference_id`);

--
-- Indices de la tabla `Sub_Category`
--
ALTER TABLE `Sub_Category`
  ADD PRIMARY KEY (`id`),
  ADD KEY `category_id` (`category_id`);

--
-- Indices de la tabla `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Admin`
--
ALTER TABLE `Admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `Category`
--
ALTER TABLE `Category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `Products`
--
ALTER TABLE `Products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
--
-- AUTO_INCREMENT de la tabla `Sales`
--
ALTER TABLE `Sales`
  MODIFY `reference_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;
--
-- AUTO_INCREMENT de la tabla `Sales_has_Products`
--
ALTER TABLE `Sales_has_Products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;
--
-- AUTO_INCREMENT de la tabla `Sub_Category`
--
ALTER TABLE `Sub_Category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;
--
-- AUTO_INCREMENT de la tabla `Users`
--
ALTER TABLE `Users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `Products`
--
ALTER TABLE `Products`
  ADD CONSTRAINT `Products_ibfk_2` FOREIGN KEY (`sub_category_id`) REFERENCES `Sub_Category` (`id`);

--
-- Filtros para la tabla `Sales`
--
ALTER TABLE `Sales`
  ADD CONSTRAINT `Sales_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `Users` (`id`);

--
-- Filtros para la tabla `Sales_has_Products`
--
ALTER TABLE `Sales_has_Products`
  ADD CONSTRAINT `Sales_has_Products_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `Products` (`id`),
  ADD CONSTRAINT `Sales_has_Products_ibfk_2` FOREIGN KEY (`sales_reference_id`) REFERENCES `Sales` (`reference_id`);

--
-- Filtros para la tabla `Sub_Category`
--
ALTER TABLE `Sub_Category`
  ADD CONSTRAINT `Sub_Category_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `Category` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
